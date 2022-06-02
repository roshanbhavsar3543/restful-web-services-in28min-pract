package com.roshan.rest.webservices.restfulwebservices.rufftest;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;



@Component
public class UserDao {
	
	private static List<User> users = new ArrayList<>();
	
	private int count =3;
	
	
	static {
		users.add(new User(1, "Roshan", new Date()));
		users.add(new User(2, "Rupesh", new Date()));
		users.add(new User(3, "Rahul", new Date()));
		System.out.println(" ******************************************* INSIDE Static Block ******************************************* ");
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++count);
		}
		users.add(user);		
		return user;
	}
	
	public User findOne(int id) {
		for(User user : users) {
			if(user.getId()==id) {
				return user;
			}
		}		
		return null;
	}
	
	
	public User deleteById(int id) {
		Iterator<User> itr = users.iterator();
		while(itr.hasNext()){
			User user = itr.next();
			if(user.getId()==id) {
				itr.remove();
				return user;
			}
		}		
		return null;
	}
	
	
	
}
