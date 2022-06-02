package com.roshan.rest.webservices.restfulwebservices.rufftest;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Autowired
	private MessageSource messageSource; // for internationalization
	
	//@RequestMapping(method = RequestMethod.GET,path = "/hello-world")
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello Spring Restful Service";
	}
	
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	
	@GetMapping(path = "/hello-world-bean/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World,%s", name));
	}
	
	
	@GetMapping(path = "/hello-world-Internationalized")
	public String helloWorldInternationalized(
			//@RequestHeader(name = "Accept-Language", required = false) Locale locale
			) {		
		 // for internationalization
		return messageSource.getMessage("morning.message", null, "Default Message", LocaleContextHolder.getLocale());		
		// "Good Morning";
	}


}
