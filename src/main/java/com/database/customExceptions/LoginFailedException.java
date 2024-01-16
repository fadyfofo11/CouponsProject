package com.database.customExceptions;

public class LoginFailedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginFailedException() {
		super("email or passord Wrong !!");
	}
}
