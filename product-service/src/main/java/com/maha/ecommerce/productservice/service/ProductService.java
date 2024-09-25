package com.maha.ecommerce.productservice.service;

import org.springframework.data.domain.Page;

import com.maha.ecommerce.productservice.dtos.ProductDto;
import com.maha.ecommerce.productservice.exceptions.CategoryNotFoundException;

public interface ProductService {
	Page<ProductDto> getAllProducts(int pageNumber, int pageSize);

	ProductDto putProduct(ProductDto product);

	ProductDto postProduct(ProductDto product) throws CategoryNotFoundException;

	ProductDto updateProductPriceById(Long id, Double price) throws Exception;

	void deleteProductById(Long id);

	ProductDto getProductById(Long id);
}
