package com.example.exceptionAdvice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exception.EmployeeNotFoundException;

@RestControllerAdvice
public class ExceptionHandler {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(EmployeeNotFoundException.class)
	public Map<String, String> handlerBussinessException(EmployeeNotFoundException exception){
		
		Map<String, String> errorMap= new HashMap<>();
		errorMap.put("Error message", exception.getMessage());
		return errorMap;
	}
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> validationhandlerMap (MethodArgumentNotValidException ex){
		
		Map<String, String> errorMap= new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(error ->{
			
			errorMap.put(error.getField(), ex.getMessage());
		});;
		
		return errorMap;
		

	}
}
