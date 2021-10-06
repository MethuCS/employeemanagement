package com.nagarro.employeemanagement.controller;

import com.nagarro.employeemanagement.entity.Employee;
import com.nagarro.employeemanagement.entity.User;
import com.nagarro.employeemanagement.repository.EmployeeRepository;
import com.nagarro.employeemanagement.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contoller class for Employees
 */

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * This method is to fetch all employees from the employees table.
     *
     * @return list of employees.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    /**
     * This method is to get a employee by id
     *
     * @param id: primary key of the employees table
     * @return: a user
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    /**
     * This method store a employee into the employees table
     *
     * @param employee: an instance in employees table.
     * @return: saved user instance
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);

    }

    /**
     * This method updates a employee instance.
     *
     * @param employee:     instance of employees.
     * @param employeeCode: primary key.
     * @return the updated employee instance.
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long employeeCode) {
       return employeeService.updateEmployee(employee,employeeCode);

    }

    /**
     * This method deletes the employee instance.
     *
     * @param id: primary key.
     */
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }


}
