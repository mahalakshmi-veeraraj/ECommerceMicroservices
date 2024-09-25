package com.maha.ecommerce.productservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String message;

	public CategoryNotFoundException(Long id, String message) {
		this.id = id;
		this.message = message;
	}
}
