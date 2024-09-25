package com.maha.ecommerce.user_service.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "token")
@Getter
@Setter
public class Token extends BaseModel {
	private String value;
	@ManyToOne
	private User user;
	private Date expiryAt;
}
