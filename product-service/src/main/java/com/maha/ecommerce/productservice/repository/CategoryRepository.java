package com.maha.ecommerce.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maha.ecommerce.productservice.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
