package com.roshan.rest.webservices.restfulwebservices;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.roshan.rest.webservices.restfulwebservices.rufftest"
	,"com.roshan.rest.webservices.restfulwebservices.jpa"})
@EntityScan({"com.roshan.rest.webservices.restfulwebservices.rufftest","com.roshan.rest.webservices.restfulwebservices.rufftest.jpa"})
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	System.out.println("git");
	}

}
