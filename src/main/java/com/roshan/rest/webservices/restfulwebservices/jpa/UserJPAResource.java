package com.roshan.rest.webservices.restfulwebservices.jpa;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.roshan.rest.webservices.restfulwebservices.rufftest.User;
import com.roshan.rest.webservices.restfulwebservices.rufftest.UserDao;
import com.roshan.rest.webservices.restfulwebservices.rufftest.exception.UserNotFoundException;

@RestController
public class UserJPAResource {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/jpa/users")
	public List<User> retriveAllUser() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/jpa/users/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public EntityModel<User> retriveUser(@PathVariable int id) {
		Optional<User> optionalUser = userRepository.findById(id);
		
		System.out.println(optionalUser);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User with id - " + id + " , not found.");
		}		
		User user = optionalUser.get();
				
		  //HATEOAS : for adding links to the RESPONSE EntityModel<User>
		EntityModel entityModelOfUser = EntityModel.of(user);
		  
		  WebMvcLinkBuilder linkForAllUsers
		  =WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).
		  retriveAllUser());
		  entityModelOfUser.add(linkForAllUsers.withRel("all-users"));
		return entityModelOfUser;
	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		// Below code is for adding location header in Response
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);		
	}

}
