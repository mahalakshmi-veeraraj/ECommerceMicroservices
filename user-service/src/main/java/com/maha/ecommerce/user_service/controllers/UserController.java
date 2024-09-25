package com.maha.ecommerce.user_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maha.ecommerce.user_service.dto.LoginRequestDto;
import com.maha.ecommerce.user_service.dto.LogoutRequestDto;
import com.maha.ecommerce.user_service.dto.SignUpRequestDto;
import com.maha.ecommerce.user_service.dto.UserDto;
import com.maha.ecommerce.user_service.models.Token;
import com.maha.ecommerce.user_service.models.User;
import com.maha.ecommerce.user_service.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("signup")
	public UserDto signUp(@RequestBody SignUpRequestDto signUpRequestDTO) {
		User user = userService.signUp(signUpRequestDTO.getEmail(), signUpRequestDTO.getName(),
				signUpRequestDTO.getPassword());
		return UserDto.from(user);
	}

	@PostMapping("login")
	public ResponseEntity<Object> login(@RequestBody LoginRequestDto loginRequestDto) {
		try {
			Token token = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

			return new ResponseEntity<Object>(token, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("logout")
	public ResponseEntity<String> logout(@RequestBody LogoutRequestDto logoutRequestDto) {
		try {
			userService.logout(logoutRequestDto.getToken());

			return new ResponseEntity<String>("Logout Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("validate/{token}")
	public ResponseEntity<?> validateToken(@PathVariable("token") String token) {
		try {
			User user = userService.validateToken(token);
			return ResponseEntity.status(HttpStatus.OK).body(UserDto.from(user));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
