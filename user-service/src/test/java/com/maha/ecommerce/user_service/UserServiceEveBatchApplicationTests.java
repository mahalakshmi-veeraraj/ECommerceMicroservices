package com.maha.ecommerce.user_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceEveBatchApplicationTests {
//	@Autowired
//	private JpaRegisteredClientRepository jpaRegisteredClientRepository;

	@Test
	void contextLoads() {
	}

//	@Test
//	public void addSampleRegisteredClient() {
//		RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString()).clientId("oidc-client") // scaler
//				.clientSecret("$2a$12$G2ZP0f0AZdWxCHPVp8Y75ueMJVtjreFE/9uSqBDxqs9QoC9/cLndu")
//				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//				.redirectUri("https://oauth.pstmn.io/v1/callback")
//				.postLogoutRedirectUri("https://oauth.pstmn.io/v1/callback").scope(OidcScopes.OPENID)
//				.scope(OidcScopes.PROFILE).scope("ADMIN")
//				.clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build()).build();
//
//		jpaRegisteredClientRepository.save(oidcClient);
//	}
}