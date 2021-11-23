package com.springbootpeserta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class TidakKetemuException extends Exception {
	
	public TidakKetemuException (String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
