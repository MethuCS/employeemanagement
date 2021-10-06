package com.nagarro.employeemanagement.service.impl;

import com.nagarro.employeemanagement.entity.Employee;
import com.nagarro.employeemanagement.repository.EmployeeRepository;
import com.nagarro.employeemanagement.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepository.getOne(id);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        Employee existingEmployee = this.employeeRepository.getOne(id);
        BeanUtils.copyProperties(employee,existingEmployee,"employee_code");
        return employeeRepository.saveAndFlush(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
