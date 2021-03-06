package com.nagarro.employeemanagement.service;

import com.nagarro.employeemanagement.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser(Long id);
    User createUser(User user);
    User updateUser(User user, Long id);
    void deleteUser(Long id);
}
