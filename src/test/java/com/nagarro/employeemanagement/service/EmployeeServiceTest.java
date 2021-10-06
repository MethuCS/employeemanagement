package com.nagarro.employeemanagement.service;

import com.nagarro.employeemanagement.entity.Employee;
import com.nagarro.employeemanagement.repository.EmployeeRepository;
import com.nagarro.employeemanagement.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @TestConfiguration
    static class Config{
        @Bean
        public EmployeeService employeeService(){
            return new EmployeeServiceImpl();
        }
    }


    @Test
    void getAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setEmployeeCode(500l);
        employee1.setEmployeeName("John");
        employee1.setEmployeeLocation("Jaffna");
        employee1.setEmail("jojn@gmail.com");
        employee1.setDateOfBirth("12 September 1995");

        Employee employee2 = new Employee();
        employee2.setEmployeeCode(501l);
        employee2.setEmployeeName("Hema");
        employee2.setEmployeeLocation("Jaffna");
        employee2.setEmail("hema@gmail.com");
        employee2.setDateOfBirth("22 September 1995");

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);

        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();

    }

    @Test
    void getEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeCode(500l);
        employee.setEmployeeName("John");
        employee.setEmployeeLocation("Jaffna");
        employee.setEmail("jojn@gmail.com");
        employee.setDateOfBirth("12 September 1995");

        when(employeeRepository.getOne(500l)).thenReturn(employee);
        Employee result = employeeService.getEmployee(500l);
        assertEquals(employee.getEmployeeName(),result.getEmployeeName());
        assertEquals(employee.getEmployeeLocation(),result.getEmployeeLocation());
        assertEquals(employee.getEmail(),result.getEmail());
        assertEquals(employee.getDateOfBirth(),result.getDateOfBirth());
    }

    @Test
    void createEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeCode(500l);
        employee.setEmployeeName("John");
        employee.setEmployeeLocation("Jaffna");
        employee.setEmail("jojn@gmail.com");
        employee.setDateOfBirth("12 September 1995");

        employeeService.createEmployee(employee);
        verify(employeeRepository, times(1)).saveAndFlush(employee);

    }

    @Test
    void updateEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeCode(500l);
        employee.setEmployeeName("John");
        employee.setEmployeeLocation("Jaffna");
        employee.setEmail("jojn@gmail.com");
        employee.setDateOfBirth("12 September 1995");

        Employee newEmployee = new Employee();

        employee.setEmployeeLocation("Colombo");
        Long id = employee.getEmployeeCode();

        given(employeeRepository.getOne(id)).willReturn(employee);

        employeeService.updateEmployee(newEmployee,id);

        verify(employeeRepository, times(1)).saveAndFlush(employee);
        verify(employeeRepository, times(1)).getOne(id);



        assertEquals(employee.getEmployeeLocation(),newEmployee.getEmployeeLocation());
        assertEquals(employee.getEmployeeName(),newEmployee.getEmployeeName());


    }

    @Test
    void deleteEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeCode(500l);
        employee.setEmployeeName("John");
        employee.setEmployeeLocation("Jaffna");
        employee.setEmail("jojn@gmail.com");
        employee.setDateOfBirth("12 September 1995");

        employeeService.deleteEmployee(employee.getEmployeeCode());
        verify(employeeRepository).deleteById(employee.getEmployeeCode());
    }
}