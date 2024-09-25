package com.maha.ecommerce.user_service.exceptions;

public class IncorrectPasswordFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncorrectPasswordFoundException(String messsage) {
		super(messsage);
	}
}
