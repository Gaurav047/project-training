package com.sapient.customer.exceptions;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sapient.customer.exceptions.response.ExceptionResponse;

@RestController
@RestControllerAdvice
public class CustomResponseException extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(DuplicateKeyException.class)
	public final ResponseEntity<Object> handleDuplicateKeyException(Exception e, WebRequest request){
		e.printStackTrace();
		String customMessage = "Duplicate key";
		
		ExceptionResponse response = new ExceptionResponse(e.getMessage(), request.getDescription(false), customMessage);
		
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
