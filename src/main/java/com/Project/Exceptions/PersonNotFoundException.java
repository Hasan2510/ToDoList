package com.Project.Exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Person not found")
public class PersonNotFoundException extends EntityNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7299156284502759611L;

	public PersonNotFoundException() {
		super();
		
	}

	public PersonNotFoundException(String message) {
		super(message);
		
	}

	
	
}
