package com.example.exception;

public class CreateAppointmentException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public CreateAppointmentException(String message) {
		super(message);
		this.message = message;
	}

	public String message() {
		return this.message;
	}
}
