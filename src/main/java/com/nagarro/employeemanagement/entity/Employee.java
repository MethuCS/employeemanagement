package com.nagarro.employeemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_code")
    private Long employeeCode;


    @NotNull
    @Size(max = 100, message = "First Name can have maximum 100 characters")
    @Column(name = "employee_name")
    private String employeeName;

    @Size(max = 500, message = "Location can have maximum 500 characters")
    @Column(name = "employee_location")
    private String employeeLocation;

    @Email
    @Size(max = 100, message = "email can have maximum 100 characters")
    @Column(name = "email")
    private String email;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    public Employee() {
    }

    public Employee(String employeeName, String employeeLocation, String email, String dateOfBirth) {
        this.employeeName = employeeName;
        this.employeeLocation = employeeLocation;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Long employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeLocation() {
        return employeeLocation;
    }

    public void setEmployeeLocation(String employeeLocation) {
        this.employeeLocation = employeeLocation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
