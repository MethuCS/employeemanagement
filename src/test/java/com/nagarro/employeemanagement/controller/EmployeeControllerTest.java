package com.nagarro.employeemanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.employeemanagement.entity.Employee;
import com.nagarro.employeemanagement.service.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(EmployeeController.class)
@WebAppConfiguration
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private EmployeeController employeeController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    void getAllEmployees() throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeCode(508l);
        employee.setEmployeeName("John");
        employee.setEmployeeLocation("Jaffna");
        employee.setEmail("jojn@gmail.com");
        employee.setDateOfBirth("12 September 1995");

        List<Employee> list = new ArrayList<>();
        list.add(employee);

        when(employeeService.getAllEmployees()).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].employeeName", Matchers.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].employeeLocation",Matchers.is("Jaffna")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email",Matchers.is("jojn@gmail.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dateOfBirth",Matchers.is("12 September 1995")));
    }

    @Test
    void getEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeCode(508l);
        employee.setEmployeeName("John");
        employee.setEmployeeLocation("Jaffna");
        employee.setEmail("jojn@gmail.com");
        employee.setDateOfBirth("12 September 1995");

        when(employeeService.getEmployee(employee.getEmployeeCode())).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/508").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeName", Matchers.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeLocation", Matchers.is("Jaffna")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("jojn@gmail.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth", Matchers.is("12 September 1995")));
    }


    @Test
    void createEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeCode(508l);
        employee.setEmployeeName("John");
        employee.setEmployeeLocation("Jaffna");
        employee.setEmail("jojn@gmail.com");
        employee.setDateOfBirth("12 September 1995");


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(this.mapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    void updateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeCode(508l);
        employee.setEmployeeName("John");
        employee.setEmployeeLocation("Jaffna");
        employee.setEmail("jojn@gmail.com");
        employee.setDateOfBirth("12 September 1995");

        Mockito.when(employeeService.getEmployee(employee.getEmployeeCode())).thenReturn(employee);
        Mockito.when(employeeService.createEmployee(employee)).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees/508")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(this.mapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeName", Matchers.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeLocation", Matchers.is("Jaffna")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("jojn@gmail.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth", Matchers.is("12 September 1995")));
    }

    @Test
    void deleteEmployee() throws Exception {

        Employee employee = new Employee();
        employee.setEmployeeCode(508l);
        employee.setEmployeeName("John");
        employee.setEmployeeLocation("Jaffna");
        employee.setEmail("jojn@gmail.com");
        employee.setDateOfBirth("12 September 1995");

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/employees/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}