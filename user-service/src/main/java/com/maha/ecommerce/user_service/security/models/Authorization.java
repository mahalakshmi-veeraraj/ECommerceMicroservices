package com.maha.ecommerce.user_service.security.models;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "authorization")
@Getter
@Setter
public class Authorization {
	@Id
	@Column
	private String id;
	private String registeredClientId;
	private String principalName;
	private String authorizationGrantType;
	@Lob
	@Column(length = 1000)
	private String authorizedScopes;
	@Lob
	@Column(length = 4000)
	private String attributes;
	@Lob
	@Column(length = 500)
	private String state;

	@Lob
	@Column(length = 4000)
	private String authorizationCodeValue;
	private Instant authorizationCodeIssuedAt;
	private Instant authorizationCodeExpiresAt;
	private String authorizationCodeMetadata;

	@Lob
	@Column(length = 4000)
	private String accessTokenValue;
	private Instant accessTokenIssuedAt;
	private Instant accessTokenExpiresAt;
	@Lob
	@Column(length = 2000)
	private String accessTokenMetadata;
	private String accessTokenType;
	@Lob
	@Column(length = 1000)
	private String accessTokenScopes;

	@Lob
	@Column(length = 4000)
	private String refreshTokenValue;
	private Instant refreshTokenIssuedAt;
	private Instant refreshTokenExpiresAt;
	@Lob
	@Column(length = 2000)
	private String refreshTokenMetadata;

	@Lob
	@Column(length = 4000)
	private String oidcIdTokenValue;
	private Instant oidcIdTokenIssuedAt;
	private Instant oidcIdTokenExpiresAt;
	@Lob
	@Column(length = 2000)
	private String oidcIdTokenMetadata;
	@Lob
	@Column(length = 2000)
	private String oidcIdTokenClaims;

	@Lob
	@Column(length = 4000)
	private String userCodeValue;
	private Instant userCodeIssuedAt;
	private Instant userCodeExpiresAt;
	@Lob
	@Column(length = 2000)
	private String userCodeMetadata;

	@Lob
	@Column(length = 4000)
	private String deviceCodeValue;
	private Instant deviceCodeIssuedAt;
	private Instant deviceCodeExpiresAt;
	@Lob
	@Column(length = 2000)
	private String deviceCodeMetadata;
}
