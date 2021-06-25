package com.example.exception;

import javax.ejb.EJBException;

public class RegisterException extends EJBException {

	private static final long serialVersionUID = -6675774710415064895L;

	private String message;

	public RegisterException(String message) {
		super(message);
		this.message = message;
	}

	public String message() {
		return this.message;
	}
}
