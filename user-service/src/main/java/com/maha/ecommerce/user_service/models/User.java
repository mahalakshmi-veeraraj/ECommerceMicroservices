package com.maha.ecommerce.user_service.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "user")
@Getter
@Setter
public class User extends BaseModel {
	private String name;
	private String email;
	private String hashedPassword;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;
	private boolean isEmailVerified;
}
