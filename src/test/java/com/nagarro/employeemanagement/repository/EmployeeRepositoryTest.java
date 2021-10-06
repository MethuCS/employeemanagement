package com.nagarro.employeemanagement.repository;

import com.nagarro.employeemanagement.entity.Employee;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    void findAllEmployees() {
        Employee employee = new Employee();
        employee.setEmployeeCode(500l);
        employee.setEmployeeName("John");
        employee.setEmployeeLocation("Jaffna");
        employee.setEmail("jojn@gmail.com");
        employee.setDateOfBirth("12 September 1995");
        employeeRepository.save(employee);
        assertNotNull(employeeRepository.findAll());
    }

    @Test
    void getEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeCode(500l);
        employee.setEmployeeName("John");
        employee.setEmployeeLocation("Jaffna");
        employee.setEmail("jojn@gmail.com");
        employee.setDateOfBirth("12 September 1995");
        employeeRepository.save(employee);
        assertNotNull(employeeRepository.getOne(employee.getEmployeeCode()));

    }

    @Test
    void saveEmployee() {

        Employee employee = new Employee();
        employee.setEmployeeCode(508l);
        employee.setEmployeeName("John");
        employee.setEmployeeLocation("Jaffna");
        employee.setEmail("jojn@gmail.com");
        employee.setDateOfBirth("12 September 1995");

        employeeRepository.saveAndFlush(employee);
        Employee currentEmployee = employeeRepository.getOne(employee.getEmployeeCode());
        assertNotNull(employee);
        assertNotNull(currentEmployee);
        assertEquals(currentEmployee.getEmployeeName(), employee.getEmployeeName());
        assertEquals(currentEmployee.getEmployeeLocation(), employee.getEmployeeLocation());
        assertEquals(currentEmployee.getEmail(), employee.getEmail());

    }

    @Test
    public void deleteEmployeeByID() {
        Employee employee = new Employee();
        employee.setEmployeeCode(508l);
        employee.setEmployeeName("John");
        employee.setEmployeeLocation("Jaffna");
        employee.setEmail("jojn@gmail.com");
        employee.setDateOfBirth("12 September 1995");

        employeeRepository.save(employee);
        employeeRepository.deleteById(employee.getEmployeeCode());
    }
}