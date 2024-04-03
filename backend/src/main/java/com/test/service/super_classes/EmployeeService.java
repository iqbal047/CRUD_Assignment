package com.test.service.super_classes;

import com.test.dao.EmployeeRepository;
import com.test.dto.Response;
import com.test.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.List;

public interface EmployeeService extends CrudService<Employee, Long> {

    Response storeData(Employee data);

    Response<Page<Employee>> getAll(Pageable pageable);

    Response<Employee> getById(Long id);

    Response delete(Long id);

    String validate(Employee data);

    String checkDuplicate(Employee data);

    byte[] generateCSV();


}
