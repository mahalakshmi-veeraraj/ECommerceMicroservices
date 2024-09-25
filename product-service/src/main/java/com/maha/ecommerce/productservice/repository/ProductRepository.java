package com.maha.ecommerce.productservice.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maha.ecommerce.productservice.models.Product;

import jakarta.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Transactional
	@Modifying
	@Query("UPDATE product p SET p.price = :price, p.updatedAt = :updatedAt WHERE p.id = :id")
	void updateProductPriceById(@Param("id") Long id, @Param("price") Double price,
			@Param("updatedAt") LocalDateTime updatedAt);
}
