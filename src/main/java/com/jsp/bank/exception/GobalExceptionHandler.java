package com.jsp.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.bank.util.ApiResponse;

@RestControllerAdvice
public class GobalExceptionHandler {
	@ExceptionHandler(InsufficeientBalanceException.class)
	public ResponseEntity<ApiResponse<String>> handleInsufficientBalanceException(InsufficeientBalanceException exception)
	{
		ApiResponse<String> apiResponse = new ApiResponse<String>(HttpStatus.CONFLICT.value(),"insufficent balance",exception.getMessage());
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleResourceNotFoundException(ResourceNotFoundException exception)
	{
		ApiResponse<String> apiResponse = new ApiResponse<String>(HttpStatus.NOT_FOUND.value(),"not found",exception.getMessage());
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ApiResponse<String>> InvalidCredentialsException(InvalidCredentialsException exception)
	{
		ApiResponse<String> apiResponse = new ApiResponse<String>(HttpStatus.UNAUTHORIZED.value(),"invalid crendentials",exception.getMessage());
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(MethodArgumentNotValidException .class)
	public ResponseEntity<ApiResponse<String>> handleValidation(MethodArgumentNotValidException exception)
	{
		ApiResponse<String> apiResponse = new ApiResponse<String>(HttpStatus.BAD_REQUEST.value(),"validation error",exception.getMessage());
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception .class)
	public ResponseEntity<ApiResponse<String>> handleValidation(Exception exception)
	{
		ApiResponse<String> apiResponse = new ApiResponse<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Something went wrong",exception.getMessage());
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
