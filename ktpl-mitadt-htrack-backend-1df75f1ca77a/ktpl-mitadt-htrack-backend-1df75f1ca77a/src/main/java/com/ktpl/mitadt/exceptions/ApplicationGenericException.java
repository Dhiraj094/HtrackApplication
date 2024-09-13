package com.ktpl.mitadt.exceptions;

import org.springframework.http.HttpStatus;

public class ApplicationGenericException extends RuntimeException {

	private static final long serialVersionUID = 28156758232392667L;
	private HttpStatus httpStatus;
	private String errorCode;

	public ApplicationGenericException(String errorCode, String message, HttpStatus httpStatus) {
		super(message);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}