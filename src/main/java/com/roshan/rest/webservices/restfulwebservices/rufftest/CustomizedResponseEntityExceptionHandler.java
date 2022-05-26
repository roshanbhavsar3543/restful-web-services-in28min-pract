package com.roshan.rest.webservices.restfulwebservices.rufftest;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import com.roshan.rest.webservices.restfulwebservices.rufftest.exception.UserNotFoundException;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleCustomException(Exception ex, WebRequest request) {

		CustomeResponseStructure responseStructure = new CustomeResponseStructure(new Date(), ex.getMessage(), request.getDescription(false));
				
		return new ResponseEntity<>(responseStructure,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {

		CustomeResponseStructure responseStructure = new CustomeResponseStructure(new Date(), ex.getMessage(), request.getDescription(false));
				
		return new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		CustomeResponseStructure customeResponseStructure = new CustomeResponseStructure(new Date(), "Validation Failed", ex.getBindingResult().toString());
				
		return new ResponseEntity(customeResponseStructure,HttpStatus.BAD_REQUEST);
	}

}
