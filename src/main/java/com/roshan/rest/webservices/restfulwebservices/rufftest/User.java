package com.roshan.rest.webservices.restfulwebservices.rufftest;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;

@Entity
//@Table(name = "USER_DETAILS")
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	
	//@Min(value =2, message = "Name Should have atleast 2 characters.")
	private String name;
	
	@Past(message = "The Birth Date should be in Past")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthDate;
	
	@OneToMany(mappedBy = "user")
	private List<UserPost> posts;
	
	public User() {
		super();
		
	}

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

	public List<UserPost> getPosts() {
		return posts;
	}

	public void setPosts(List<UserPost> posts) {
		this.posts = posts;
	}	

}
