package com.nagarro.employeemanagement.service;

import com.nagarro.employeemanagement.entity.User;
import com.nagarro.employeemanagement.repository.UserRepository;
import com.nagarro.employeemanagement.service.impl.UserServiceImpl;
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
class UserServiceTest {


    @Autowired
    UserService userService;

    @MockBean
    private UserRepository userRepository;

    @TestConfiguration
    static class Config{
        @Bean
        public UserService userService(){
            return new UserServiceImpl();
        }
    }

    @Test
    void getAllUsers() {
        User user = new User();
        user.setUserId(1l);
        user.setUserName("methu");
        user.setPassword("1234");
        List<User> userList = new ArrayList<>();
        userList.add(user);

        when(userRepository.findAll()).thenReturn(userList);
        List<User> result = userService.getAllUsers();

        assertEquals(1, result.size());
        verify(userRepository, times(1)).findAll();

    }

    @Test
    void getUser() {
        User user = new User();
        user.setUserId(1l);
        user.setUserName("methu");
        user.setPassword("1234");

        when(userRepository.getOne(1l)).thenReturn(user);
        User result = userService.getUser(1l);
        assertEquals(user.getUserName(),result.getUserName());
        assertEquals(user.getPassword(),result.getPassword());
    }

    @Test
    void createUser() {
        User user = new User();
        user.setUserId(1l);
        user.setUserName("methu");
        user.setPassword("1234");



        userService.createUser(user);
        verify(userRepository, times(1)).saveAndFlush(user);
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setUserId(1l);
        user.setUserName("methu");
        user.setPassword("1234");

        User newUser = new User();

        user.setUserName("hema");

        Long id = user.getUserId();
        given(userRepository.getOne(user.getUserId())).willReturn(user);

        userService.updateUser(newUser,id);

        verify(userRepository, times(1)).saveAndFlush(user);
        verify(userRepository, times(1)).getOne(id);

        assertEquals(user.getUserName(),newUser.getUserName());
    }

    @Test
    void deleteUser() {
        User user = new User();
        user.setUserId(1l);
        user.setUserName("methu");
        user.setPassword("1234");

        userService.deleteUser(user.getUserId());
        verify(userRepository).deleteById(user.getUserId());
    }
}