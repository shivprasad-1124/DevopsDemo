package com.srs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(exception=AppException.class)
	public ResponseEntity<?> handleAppException(AppException e){
		return new ResponseEntity<>(e.getMessage(),e.getHttpStatus());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> Exception(){
		Exception e = new Exception();
		return new ResponseEntity<>("Something Went Wrong!",HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
