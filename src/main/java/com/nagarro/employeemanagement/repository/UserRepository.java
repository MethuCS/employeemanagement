package com.nagarro.employeemanagement.repository;

import com.nagarro.employeemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

}
