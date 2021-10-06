package com.nagarro.employeemanagement.repository;

import com.nagarro.employeemanagement.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    void findAllEmployees() {

        User user = new User();
        user.setUserId(1l);
        user.setUserName("Dolla");
        user.setPassword("1234");
        userRepository.save(user);
        assertNotNull(userRepository.findAll());
    }

    @Test
    void getEmployee() {
        User user = new User();
        user.setUserId(1l);
        user.setUserName("Dolla");
        user.setPassword("1234");
        userRepository.save(user);
        assertNotNull(userRepository.getOne(user.getUserId()));

    }

    @Test
    void saveEmployee() {

        User user = new User();
        user.setUserId(1l);
        user.setUserName("Dolla");
        user.setPassword("1234");

        userRepository.saveAndFlush(user);
        User currentEmployee = userRepository.getOne(user.getUserId());
        assertNotNull(user);
        assertNotNull(currentEmployee);
        assertEquals(currentEmployee.getUserName(), user.getUserName());
        assertEquals(currentEmployee.getPassword(), user.getPassword());

    }

    @Test
    public void deleteEmployeeByID() {
        User user = new User();
        user.setUserId(1l);
        user.setUserName("Dolla");
        user.setPassword("1234");

        userRepository.save(user);
        userRepository.deleteById(user.getUserId());
    }


}