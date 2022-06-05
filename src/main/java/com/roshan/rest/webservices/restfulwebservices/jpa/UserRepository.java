package com.roshan.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roshan.rest.webservices.restfulwebservices.rufftest.User;
import com.roshan.rest.webservices.restfulwebservices.rufftest.UserPost;

public interface UserRepository extends JpaRepository<User, Integer> {
 //Dao layer or repository layer
}
