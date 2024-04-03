package com.test.service;

import com.test.dao.EmployeeRepository;
import com.test.dto.Response;
import com.test.model.Employee;
import com.test.service.super_classes.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.List;

import static com.test.constants.enums.OperationStatus.FAILURE;
import static com.test.constants.enums.OperationStatus.SUCCESS;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public Response storeData(Employee data) {
        String validationMsg = validate(data);
        if (validationMsg == null) {
            repository.save(data);
            return new Response(SUCCESS, "Successfully stored data", null);
        } else {
            return new Response(FAILURE, validationMsg, null);
        }
    }

    @Override
    public Response<Page<Employee>> getAll(Pageable pageable) {
        Page<Employee> page = repository.findByActive(true, pageable);
        return new Response<>(SUCCESS, null, page);
    }

    @Override
    public Response<Employee> getById(Long id) {
        Employee employee = repository.findById(id).orElse(new Employee());
        return new Response<>(SUCCESS, null, employee);
    }

    @Override
    public Response delete(Long id) {
        repository.softDeleteById(id);
        return new Response(SUCCESS, "Deleted successfully", null);
    }

    @Override
    public String validate(Employee data) {
        return checkDuplicate(data);
    }

    @Override
    public String checkDuplicate(Employee data) {
//        boolean employeenameExists;
//        if (data.hasId()) {
//            employeenameExists = repository.existsByEmployeename(data.getEmployeename(), data.getId());
//        } else {
//            employeenameExists = repository.existsByEmployeename(data.getEmployeename());
//        }
//        return employeenameExists ? "Failed to register. Employee already exists" : null;
        return null;
    }

    @Override
    public byte[] generateCSV() {
        List<Employee> employees = repository.findByActive(true);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        writer.println("EmployeeId,EmployeeName,Address,MobileNo,Age,Salary");
        for (Employee employee : employees) {
            writer.println(employee.getId() + "," + employee.getEmployeeName() + "," + employee.getAddress() + ","
                    + employee.getMobileNo() + "," + employee.getAge() + "," + employee.getSalary());
        }
        writer.flush();
        writer.close();
        return outputStream.toByteArray();
    }

}
