package com.ktpl.mitadt.exceptions;

public class ExceptionResponse {

	private String errorCode;
	private String errorMessage;

	public ExceptionResponse() {
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}