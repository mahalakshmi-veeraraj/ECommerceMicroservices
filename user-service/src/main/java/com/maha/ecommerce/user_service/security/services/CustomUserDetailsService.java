package com.maha.ecommerce.user_service.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.maha.ecommerce.user_service.models.User;
import com.maha.ecommerce.user_service.repository.UserRepository;
import com.maha.ecommerce.user_service.security.models.CustomUserDetails;

@Service
@JsonDeserialize
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByEmail(username);
		if (optionalUser.isEmpty()) {
			throw new UsernameNotFoundException("User with email " + username + " doesn't exist.");
		}
		CustomUserDetails customUserDetails = new CustomUserDetails(optionalUser.get());
		return customUserDetails;
	}

}
