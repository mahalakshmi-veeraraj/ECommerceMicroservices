package com.maha.ecommerce.productservice.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.maha.ecommerce.productservice.dtos.ExceptionDto;
import com.maha.ecommerce.productservice.exceptions.CategoryNotFoundException;
import com.maha.ecommerce.productservice.exceptions.ProductNotFoundException;

@ControllerAdvice
public class ExceptionHandlers {
	@ExceptionHandler
	public ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException e) {
		ExceptionDto exceptionDto = new ExceptionDto();
		exceptionDto.setMessage(e.getMessage());
		return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ExceptionDto> handleCategoryNotFoundException(CategoryNotFoundException e) {
		ExceptionDto exceptionDto = new ExceptionDto();
		exceptionDto.setMessage(e.getMessage());
		return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
	}
}
