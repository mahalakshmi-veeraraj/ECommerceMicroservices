package com.maha.ecommerce.productservice.models;

import com.maha.ecommerce.productservice.dtos.ProductDto;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "product")
@Getter
@Setter
public class Product extends BaseModel {
	private String title;
	private String description;
	private Double price;
	private String image;
	@ManyToOne
	private Category category;
	private Integer qty;
	private Integer numberOfOrders;

	public static Product covert(ProductDto productDto) {
		Product product = new Product();
		product.setTitle(productDto.getTitle());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setImage(productDto.getImage());
		product.setQty(productDto.getQty());
		product.setNumberOfOrders(productDto.getNumberOfOrders());
		product.setId(productDto.getId());
		product.setCreatedAt(productDto.getCreatedAt());
		product.setUpdatedAt(productDto.getUpdatedAt());
		Category category = new Category();
		category.setTitle(productDto.getCategory().getTitle());
		category.setId(productDto.getCategory().getId());
		category.setCreatedAt(productDto.getCategory().getCreatedAt());
		category.setUpdatedAt(productDto.getCategory().getUpdatedAt());
		product.setCategory(category);
		return product;
	}
}

//1				->			1			=> M:1 Many to One.
//Product ------------------	Category
//M				<-			1
//====================================
//M							1

//Movie  ---------------------- 	Actor	=> Many to Many.
//1				->				M
//M				<-				1
//=====================================
//M								M
