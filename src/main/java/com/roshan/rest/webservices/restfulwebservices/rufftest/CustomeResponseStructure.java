package com.roshan.rest.webservices.restfulwebservices.rufftest;

import java.util.Date;

public class CustomeResponseStructure {
	
	private Date timeStamp;
	
	private String message;
	
	private String details;
	

	public CustomeResponseStructure(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
	
	
	

}
