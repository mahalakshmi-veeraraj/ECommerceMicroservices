package com.maha.ecommerce.productservice.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.maha.ecommerce.productservice.dtos.UserDto;

@Service
public class AuthCommons {
	@Autowired
	private RestTemplate restTemplate;

	public UserDto validateToken(String token) {
		// Call the userService to validate the token.

		ResponseEntity<UserDto> responseEntity = restTemplate
				.getForEntity("http://localhost:4141/user/validate/" + token, UserDto.class);

		if (responseEntity.getBody() == null) {
			// token is invalid.
			// throw some exception here.
			return null;
		}

		return responseEntity.getBody();
	}
}
