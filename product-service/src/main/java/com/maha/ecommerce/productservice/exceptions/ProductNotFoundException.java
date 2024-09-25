package com.maha.ecommerce.productservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String message;

	public ProductNotFoundException(Long id, String message) {
		super(message);
		this.id = id;
	}
}
