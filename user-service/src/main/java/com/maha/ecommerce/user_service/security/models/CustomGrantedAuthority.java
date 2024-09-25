package com.maha.ecommerce.user_service.security.models;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.maha.ecommerce.user_service.models.Role;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {
	private static final long serialVersionUID = 1L;
	private String authority;

	public CustomGrantedAuthority(Role role) {
		this.authority = role.getValue();
	}

	@Override
	public String getAuthority() {

		return authority;
	}

}
