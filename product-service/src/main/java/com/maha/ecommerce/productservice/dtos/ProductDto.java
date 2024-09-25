package com.maha.ecommerce.productservice.dtos;

import com.maha.ecommerce.productservice.models.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto extends BaseDto {
	private String title;
	private String description;
	private Double price;
	private String image;
	private CategoryDto category;
	private Integer qty;
	private Integer numberOfOrders;

	public static ProductDto covert(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setTitle(product.getTitle());
		productDto.setDescription(product.getDescription());
		productDto.setPrice(product.getPrice());
		productDto.setImage(product.getImage());
		productDto.setQty(product.getQty());
		productDto.setNumberOfOrders(product.getNumberOfOrders());
		productDto.setId(product.getId());
		productDto.setCreatedAt(product.getCreatedAt());
		productDto.setUpdatedAt(product.getUpdatedAt());
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setTitle(product.getCategory().getTitle());
		categoryDto.setId(product.getCategory().getId());
		categoryDto.setCreatedAt(product.getCategory().getCreatedAt());
		categoryDto.setUpdatedAt(product.getCategory().getUpdatedAt());
		productDto.setCategory(categoryDto);
		return productDto;
	}
}
