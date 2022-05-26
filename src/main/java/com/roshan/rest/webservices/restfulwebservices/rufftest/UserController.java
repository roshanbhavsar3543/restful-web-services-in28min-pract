package com.roshan.rest.webservices.restfulwebservices.rufftest;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.roshan.rest.webservices.restfulwebservices.rufftest.exception.UserNotFoundException;

@RestController
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	
	@GetMapping(path = "/users")
	public List<User> retriveAllUser(){
		return userDao.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public User retriveUser(@PathVariable int id){		
		User user = userDao.findOne(id);
		
		if(user ==null) {
			throw new UserNotFoundException("User with id - "+id+" , not found.");
		}
				
		return user;
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		User savedUser = userDao.save(user);
		//System.out.println(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		//System.out.println(location + " **************");
		return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
		User user  = userDao.deleteById(id);
		
		if(user == null) {
			throw new UserNotFoundException("User with id - "+id+" , not found.");
		}
	}
	
}
