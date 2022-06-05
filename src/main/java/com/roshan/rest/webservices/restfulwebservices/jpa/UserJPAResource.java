package com.roshan.rest.webservices.restfulwebservices.jpa;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
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
import com.roshan.rest.webservices.restfulwebservices.rufftest.UserPost;
import com.roshan.rest.webservices.restfulwebservices.rufftest.exception.UserNotFoundException;

@RestController
public class UserJPAResource {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserPostRepository userPostRepository;

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
	
	
	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<UserPost> retriveAllPosts(@PathVariable int id){
		Optional<User> userOption =  userRepository.findById(id);		
		if(!userOption.isPresent()) {
			throw new UserNotFoundException("User with id - " + id + " , not found.");
		}		
		return userOption.get().getPosts();
	}
	
	@PostMapping(path = "/jpa/users/{id}/posts")
	public ResponseEntity<User> savePost(@PathVariable int id, @Valid @RequestBody UserPost userPost) {
		Optional<User> userOption =  userRepository.findById(id);		
		if(!userOption.isPresent()) {
			throw new UserNotFoundException("User with id - " + id + " , not found.");
		}
		User user = userOption.get();
		userPost.setUser(user);
		userPostRepository.save(userPost);
		
		
		userRepository.save(user);
		// Below code is for adding location header in Response
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userPost.getId())
						.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@GetMapping(path = "/jpa/users/{id}/posts/{postId}")
	public UserPost retrivePostForUser(@PathVariable int id,@PathVariable int postId){
		Optional<UserPost> userOption =  userPostRepository.findById(postId);		
		if(!userOption.isPresent()) {
			throw new UserNotFoundException("User with id - " + id + " , not found.");
		}		
		return userOption.get();
	}
	
	

}
