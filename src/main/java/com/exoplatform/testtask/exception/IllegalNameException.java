package com.exoplatform.testtask.exception;

/**
 * Thrown when QNameParser attempts to parse name, which doesn't match the template
 *
 * @author  Denys Tymchuk
 */
public class IllegalNameException extends Exception {

	public IllegalNameException() {
		super();
	}

	public IllegalNameException(String message) {
		super(message);
	}
}
