package com.nagarro.employeemanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.employeemanagement.entity.User;
import com.nagarro.employeemanagement.service.UserService;
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
@WebMvcTest(UserController.class)
@WebAppConfiguration
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    void getAllUsers() throws Exception {
        User user = new User();
        user.setUserId(1l);
        user.setUserName("Edward");
        user.setPassword("1234");

        List<User> list = new ArrayList<>();
        list.add(user);

        when(userService.getAllUsers()).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName", Matchers.is("Edward")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password",Matchers.is("1234")));
    }

    @Test
    void getUser() throws Exception {
        User user = new User();
        user.setUserId(1l);
        user.setUserName("Edward");
        user.setPassword("1234");

        when(userService.getUser(user.getUserId())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName", Matchers.is("Edward")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("1234")));
    }

    @Test
    void createUser() throws Exception {
        User user = new User();
        user.setUserId(1l);
        user.setUserName("Edward");
        user.setPassword("1234");


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(this.mapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    void updateUser() throws Exception {
        User user = new User();
        user.setUserId(1l);
        user.setUserName("Edward");
        user.setPassword("1234");

        Mockito.when(userService.getUser(user.getUserId())).thenReturn(user);
        Mockito.when(userService.createUser(user)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(this.mapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName", Matchers.is("Edward")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("1234")));
    }

    @Test
    void deleteUser() throws Exception {
        User user = new User();
        user.setUserId(1l);
        user.setUserName("Edward");
        user.setPassword("1234");

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}