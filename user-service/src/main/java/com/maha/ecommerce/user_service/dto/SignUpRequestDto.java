package com.maha.ecommerce.user_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
	private String email;
	private String name;
	private String password;
}
