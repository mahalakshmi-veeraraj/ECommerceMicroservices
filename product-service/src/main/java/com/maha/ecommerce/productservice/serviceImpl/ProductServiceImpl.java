package com.maha.ecommerce.productservice.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.maha.ecommerce.productservice.dtos.ProductDto;
import com.maha.ecommerce.productservice.exceptions.CategoryNotFoundException;
import com.maha.ecommerce.productservice.models.Category;
import com.maha.ecommerce.productservice.models.Product;
import com.maha.ecommerce.productservice.repository.CategoryRepository;
import com.maha.ecommerce.productservice.repository.ProductRepository;
import com.maha.ecommerce.productservice.service.ProductService;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public Page<ProductDto> getAllProducts(int pageNumber, int pageSize) {
		Page<Product> productPage = productRepository
				.findAll(PageRequest.of(pageNumber - 1, pageSize, Sort.by("price").ascending()));
		// Convert Page<Product> to Page<ProductDto>.
		List<ProductDto> productDtos = productPage.getContent().stream().map(ProductDto::covert)
				.collect(Collectors.toList());
		return new PageImpl<ProductDto>(productDtos, productPage.getPageable(), productPage.getTotalElements());
	}

	@Override
	@Transactional
	public ProductDto putProduct(ProductDto productDto) {
		Product putProduct = Product.covert(productDto);
		putProduct.setUpdatedAt(LocalDateTime.now());
		putProduct.setCreatedAt(LocalDateTime.now());
		putProduct.getCategory().setUpdatedAt(LocalDateTime.now());
		return ProductDto.covert(productRepository.save(putProduct));
	}

	@Override
	@Transactional
	public ProductDto postProduct(ProductDto productDto) throws CategoryNotFoundException {
		Product product = Product.covert(productDto);
		Category category = product.getCategory();
		Optional<Category> fetchedCategory = categoryRepository.findById(category.getId());
		if (!fetchedCategory.isPresent()) {
			// we need to save the category.
			category.setCreatedAt(LocalDateTime.now());
			category.setUpdatedAt(LocalDateTime.now());
			Category savedCategory = categoryRepository.save(category);
			product.setCategory(savedCategory);
		}
		else {
			product.setCategory(fetchedCategory.get());
		}
		
		product.setCreatedAt(LocalDateTime.now());
		product.setUpdatedAt(LocalDateTime.now());
		Product savedProduct = productRepository.save(product);

		Optional<Category> optionalCategory = categoryRepository.findById(savedProduct.getCategory().getId());
		Category category2 = optionalCategory.get();
		savedProduct.setCategory(category2);
		return ProductDto.covert(savedProduct);
	}

	@Override
	@Transactional
	public ProductDto updateProductPriceById(Long id, Double price) throws Exception {
		try {
			productRepository.updateProductPriceById(id, price, LocalDateTime.now());
			return ProductDto.covert(productRepository.findById(id).get());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional
	public void deleteProductById(Long id) {

		productRepository.deleteById(id);
	}

	@Override
	public ProductDto getProductById(Long id) {
		ProductDto cachedProductDto = (ProductDto) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCTS_" + id);
		if (cachedProductDto != null) {
			return cachedProductDto;
		}

		ProductDto productDto = ProductDto.covert(productRepository.findById(id).get());
		// Store the data inside the Redis.
		/*
		 * Id : Key Map Name : PRODUCTS Value : Product Object
		 */
		redisTemplate.opsForHash().put("PRODUCTS", "PRODUCTS_" + id, productDto);
		return productDto;
	}

}
