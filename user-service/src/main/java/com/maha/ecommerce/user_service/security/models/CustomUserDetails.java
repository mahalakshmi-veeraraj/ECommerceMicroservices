package com.maha.ecommerce.user_service.security.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.maha.ecommerce.user_service.models.Role;
import com.maha.ecommerce.user_service.models.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonDeserialize
// This CustomUserDetails class will act like a user class for Spring Security.
public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private List<CustomGrantedAuthority> authorities;
	private Long userId;

	public CustomUserDetails(User user) {
		this.username = user.getEmail();
		this.password = user.getHashedPassword();
		this.userId = user.getId();
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.enabled = true;

		// In the granted authorities, we need to add the roles.
		this.authorities = new ArrayList<>();
		for (Role role : user.getRoles()) {
			authorities.add(new CustomGrantedAuthority(role));
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return username;
	}

	@Override
	public boolean isAccountNonExpired() {

		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {

		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {

		return enabled;
	}

	public Long getUserId() {

		return userId;
	}
}
