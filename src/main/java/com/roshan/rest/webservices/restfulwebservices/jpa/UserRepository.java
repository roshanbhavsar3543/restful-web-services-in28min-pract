package com.roshan.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roshan.rest.webservices.restfulwebservices.rufftest.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
