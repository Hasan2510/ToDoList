package com.Project.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Achievement not there" )
public class AchievementNotFoundException extends EntityNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7868989731140918508L;

	public AchievementNotFoundException() {
		super();
		
	}

	public AchievementNotFoundException(String message) {
		super(message);
		
	}
	
	
	

}
