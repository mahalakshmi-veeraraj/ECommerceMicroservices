package com.maha.ecommerce.user_service.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.maha.ecommerce.user_service.exceptions.IncorrectPasswordFoundException;
import com.maha.ecommerce.user_service.exceptions.UserNotFoundException;
import com.maha.ecommerce.user_service.models.Token;
import com.maha.ecommerce.user_service.models.User;
import com.maha.ecommerce.user_service.repository.TokenRepository;
import com.maha.ecommerce.user_service.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TokenRepository tokenRepository;

	public User signUp(String email, String name, String password) {
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setHashedPassword(bCryptPasswordEncoder.encode(password));
		user.setEmailVerified(true);

		// save the user object to the database.
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	public Token login(String email, String password) throws UserNotFoundException, IncorrectPasswordFoundException {
		Optional<User> optionalUser = userRepository.findByEmail(email);

		if (optionalUser.isEmpty()) {

			throw new UserNotFoundException("User with email " + email + " doesn't exist");
		}

		User user = optionalUser.get();
		if (!bCryptPasswordEncoder.matches(password, user.getHashedPassword())) {

			throw new IncorrectPasswordFoundException("Incorrect password found for the user with email " + email);
		}

		// Login Successful.

		Token token = generateToken(user); // token object does not have unique key id value.
		Token savedToken = tokenRepository.save(token); // savedToken object will have unique key id value.
		return savedToken;
	}

	public void logout(String tokenValue) throws Exception {
		Optional<Token> optionalToken = tokenRepository.findByValueAndDeleted(tokenValue, false);

		if (optionalToken.isEmpty()) {

			throw new Exception("Invalid token found");
		}

		Token token = optionalToken.get();
		token.setDeleted(true);
		tokenRepository.save(token);
	}

	public User validateToken(String token) throws Exception {
		Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedAndExpiryAtGreaterThan(token, false,
				new Date());

		if (optionalToken.isEmpty()) {
			throw new Exception("Invalid token");
		}

		return optionalToken.get().getUser();
	}

	private Token generateToken(User user) {
		LocalDate currentDate = LocalDate.now();
		LocalDate thirtyDaysLater = currentDate.plusDays(30);

		Date expirtyDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());

		Token token = new Token();
		token.setExpiryAt(expirtyDate);
		// 128 character alpha numeric string.
		token.setValue(RandomStringUtils.randomAlphanumeric(128));
		token.setUser(user);
		token.setDeleted(false);
		return token;
	}
}
