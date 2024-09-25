package com.maha.ecommerce.productservice.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto {
	private Long id;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
