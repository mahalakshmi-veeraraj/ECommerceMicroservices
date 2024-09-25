package com.maha.ecommerce.user_service.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "role")
@Getter
@Setter
public class Role extends BaseModel {
	private String value;
}
