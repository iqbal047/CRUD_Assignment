package com.test;

import com.test.dao.UserRepository;
import com.test.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class EmployeeApplication {
    public static ApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(EmployeeApplication.class, args);
//        UserRepository repo = context.getBean(UserRepository.class);
//        User user = new User();
//        user.setName("a");
//        user.setUsername("a");
//        user.setPassword("$2a$10$vgsRvizYk4HWlwQ1Rfd/iuYfl020ivIFojTn2r5JoPGi9SJT27Bp6");
//        user.setActive(true);
//        user.setRoles(Arrays.asList());
//        repo.save(user);
//        System.out.println(new BCryptPasswordEncoder().encode("1234"));
    }
}
