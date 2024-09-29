package com.maha.ecommerce.productservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import com.maha.ecommerce.productservice.dtos.ProductDto;
import com.maha.ecommerce.productservice.exceptions.ProductNotFoundException;
import com.maha.ecommerce.productservice.service.ProductService;

@SpringBootTest
public class ProductControllerTest {
	@Autowired
	private ProductController productController;

	@MockBean
	private ProductService productService;

	@Test
	void testValidGetProductById() throws ProductNotFoundException {
		ProductDto productDto = new ProductDto();
		productDto.setId(1L);
		productDto.setTitle("Macbook Pro");
		productDto.setPrice(150000.00);

		when(productService.getProductById(1L)).thenReturn(productDto);

		ResponseEntity<?> responseEntity = productController.getProductById(1L);
		ProductDto actualProductDto = (ProductDto) responseEntity.getBody();

		assertEquals(productDto, actualProductDto);
	}

	@Test
	void testInvalidGetProductById() {

		assertThrows(ProductNotFoundException.class, () -> productController.getProductById(122L));
	}

	@Test
	void testGetAllProducts() {
		List<ProductDto> expectedProducts = new ArrayList<ProductDto>();
		ProductDto p1 = new ProductDto();
		p1.setId(1L);
		p1.setTitle("iPhone 13 pro");
		expectedProducts.add(p1);

		ProductDto p2 = new ProductDto();
		p2.setId(2L);
		p2.setTitle("iPhone 14 pro");
		expectedProducts.add(p2);

		ProductDto p3 = new ProductDto();
		p3.setId(3L);
		p3.setTitle("iPhone 15 pro");
		expectedProducts.add(p3);
		
		Page<ProductDto> expectedPageProductDto = new PageImpl<ProductDto>(expectedProducts);

		Page<ProductDto> pageProductDto = (Page<ProductDto>) when(productController.getAllProducts(1, 1)).thenReturn((Page<ProductDto>) expectedPageProductDto);
		List<ProductDto> actualProducts = pageProductDto.getContent();
		assertIterableEquals(expectedProducts, actualProducts);
	}
	
	
}
