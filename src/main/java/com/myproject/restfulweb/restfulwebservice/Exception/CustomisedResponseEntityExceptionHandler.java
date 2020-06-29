/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.restfulweb.restfulwebservice.Exception;

import com.myproject.restfulweb.restfulwebservice.user.UserNotFoundException;
import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author krish
 */


@ControllerAdvice
@RestController
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex ,WebRequest web)
    {
      
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),web.getDescription(false));

        return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> userNotFoundException(Exception ex ,WebRequest web)
    {
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),web.getDescription(false));

        return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
    }
        
    
    	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
				ex.getBindingResult().toString());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
    
    
    
    
    
    }
 
  
