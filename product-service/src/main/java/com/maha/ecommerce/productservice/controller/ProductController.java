package com.maha.ecommerce.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maha.ecommerce.productservice.dtos.ProductDto;
import com.maha.ecommerce.productservice.exceptions.CategoryNotFoundException;
import com.maha.ecommerce.productservice.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;
//	@Autowired
//	private AuthCommons authCommons;

	@GetMapping("getAllProducts")
	public Page<ProductDto> getAllProducts(@RequestParam("pageNumber") int pageNumber,
			@RequestParam("pageSize") int pageSize) {

		return productService.getAllProducts(pageNumber, pageSize);
	}

	@PutMapping("putProduct")
	public ResponseEntity<ProductDto> putProduct(@RequestBody ProductDto productDto) {
		try {
			return ResponseEntity.ok(productService.putProduct(productDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("postProduct")
	public ResponseEntity<?> postProduct(@RequestBody ProductDto productDto) throws CategoryNotFoundException {
		try {
			return ResponseEntity.ok(productService.postProduct(productDto));
		} catch (CategoryNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured: " + e.getMessage());
		}
	}

	@PatchMapping("updateProductPriceById")
	public ResponseEntity<?> updateProductPriceById(@RequestBody ProductDto productDto) {
		try {
			return ResponseEntity.ok(productService.updateProductPriceById(productDto.getId(), productDto.getPrice()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
		}
	}

	@DeleteMapping("deleteProductById")
	public ResponseEntity<String> deleteProductById(@RequestParam("id") Long id) {
		try {
			productService.deleteProductById(id);
			return ResponseEntity.ok("Deleted Successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
		}
	}

	@GetMapping("getProductById")
	public ResponseEntity<?> getProductById(@RequestParam("id") Long id) {
		try {
			// Call UserService validateToken API to validate the token.
//			UserDto userDto = authCommons.validateToken(token);
//			if (userDto == null) {
//
//				return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//			}

//			boolean isAdmin = false;
//			// Role Based Access.
//			for (Role role : userDto.getRoles()) {
//				if (role.getValue().equals("ADMIN")) {
//					// provide access.
//					isAdmin = true;
//				}
//			}
//			if (!isAdmin) {
//				responseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//				return responseEntity;
//			}

			ProductDto productDto = productService.getProductById(id);
			return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
		}
	}

}
