package com.ktpl.mitadt.exceptions;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingController.class);

	@ExceptionHandler(ApplicationGenericException.class)
	public ResponseEntity<ExceptionResponse> genericException(ApplicationGenericException ex) {

		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("" + ex.getErrorCode());
		response.setErrorMessage(ex.getMessage());

		LOGGER.error("Application Exception", ex.getMessage());

		ex.printStackTrace();

		return new ResponseEntity<ExceptionResponse>(response, ex.getHttpStatus());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex) {

		ExceptionResponse response = new ExceptionResponse();

		BindingResult result = ex.getBindingResult();
		List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
		for (org.springframework.validation.FieldError fieldError : fieldErrors) {
			response.setErrorCode(fieldError.getField());
			response.setErrorMessage(fieldError.getDefaultMessage());
		}
		LOGGER.error("methodArgumentNotValidException", ex.getMessage());

		ex.printStackTrace();

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}
}