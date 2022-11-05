package com.hotdog.server.exception;

import org.springframework.http.HttpStatus;

public class AuthException extends CustomException{
	public AuthException(String message, HttpStatus status)
	{
		super(message,status);
	}
}
