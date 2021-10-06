package com.nagarro.employeemanagement.service.impl;

import com.nagarro.employeemanagement.entity.User;
import com.nagarro.employeemanagement.repository.UserRepository;
import com.nagarro.employeemanagement.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User updateUser(User user, Long id) {
        User existingUser = this.userRepository.getOne(id);
        BeanUtils.copyProperties(user,existingUser,"user_id");
        return userRepository.saveAndFlush(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }

}
