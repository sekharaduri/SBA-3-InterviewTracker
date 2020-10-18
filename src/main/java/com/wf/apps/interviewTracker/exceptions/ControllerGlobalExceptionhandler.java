package com.wf.apps.interviewTracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerGlobalExceptionhandler {
	@ExceptionHandler(CustomExceptions.class)
	public ResponseEntity<CustomExceptions> fieldValidationHandler(CustomExceptions customExceptions)
	{
		return new ResponseEntity<CustomExceptions>(customExceptions,HttpStatus.BAD_REQUEST);
	}

}
