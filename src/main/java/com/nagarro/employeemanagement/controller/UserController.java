package com.nagarro.employeemanagement.controller;

import com.nagarro.employeemanagement.entity.User;
import com.nagarro.employeemanagement.repository.UserRepository;
import com.nagarro.employeemanagement.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * This method is to fetch all users from the users table.
     *
     * @return list of users.
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * This method is to get a user by id
     *
     * @param id: primary key of the users table
     * @return: a user
     */
    @GetMapping
    @RequestMapping("{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    /**
     * This method store a user into the users table
     *
     * @param user: an instance in users table.
     * @return: saved user instance
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * This method updates a user instance.
     *
     * @param user:   instance of users.
     * @param userId: primary key.
     * @return the updated user instance.
     */
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") Long userId) {
        return userService.updateUser(user, userId);

    }

    /**
     * This method deletes the user instance.
     *
     * @param id: primary key.
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


}
