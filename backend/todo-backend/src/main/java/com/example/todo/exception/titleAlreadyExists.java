package com.example.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class titleAlreadyExists extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	public titleAlreadyExists(String message) {
        super(message);
    }
}
