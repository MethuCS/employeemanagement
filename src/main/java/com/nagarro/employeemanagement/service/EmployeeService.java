package com.nagarro.employeemanagement.service;

import com.nagarro.employeemanagement.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployee(Long id);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee, Long id);
    void deleteEmployee(Long id);
}
