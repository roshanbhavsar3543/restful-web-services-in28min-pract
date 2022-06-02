package com.roshan.rest.webservices.restfulwebservices.rufftest;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
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
	public List<User> retriveAllUser() {
		return userDao.findAll();
	}

	@GetMapping(path = "/users/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public EntityModel<User> retriveUser(@PathVariable int id) {
		User user = userDao.findOne(id);

		if (user == null) {
			throw new UserNotFoundException("User with id - " + id + " , not found.");
		}

		
		  //HATEOAS : for adding links to the RESPONSE EntityModel<User>
		EntityModel entityModelOfUser = EntityModel.of(user);
		  
		  WebMvcLinkBuilder linkForAllUsers
		  =WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).
		  retriveAllUser());
		  entityModelOfUser.add(linkForAllUsers.withRel("all-users"));
		 

			/*
			 * Resource<User> resource = new Resource<User>(user);
			 * 
			 * ControllerLinkBuilder linkTo =
			 * linkTo(methodOn(this.getClass()).retriveAllUser());
			 * resource.add(linkTo.withRel("all-users"));
			 */

		

		return entityModelOfUser;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		User savedUser = userDao.save(user);

		// Below code is for adding location header in Response
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {

		User user = userDao.deleteById(id);

		if (user == null) {
			throw new UserNotFoundException("User with id - " + id + " , not found.");
		}
	}

}
