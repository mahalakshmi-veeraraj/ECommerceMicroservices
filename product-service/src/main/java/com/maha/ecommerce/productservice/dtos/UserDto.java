package com.maha.ecommerce.productservice.dtos;

import java.util.List;

import com.maha.ecommerce.productservice.models.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private String name;
	private String email;
	private List<Role> roles;
}
