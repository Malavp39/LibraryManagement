package com.demo.exception;

public class BookException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BookException(String message) {
		this.message = message;
	}
}
