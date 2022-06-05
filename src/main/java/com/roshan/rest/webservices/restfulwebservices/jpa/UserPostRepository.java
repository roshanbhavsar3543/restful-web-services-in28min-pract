package com.roshan.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roshan.rest.webservices.restfulwebservices.rufftest.User;
import com.roshan.rest.webservices.restfulwebservices.rufftest.UserPost;

public interface UserPostRepository extends JpaRepository<UserPost, Integer> {

}
