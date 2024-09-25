package com.maha.ecommerce.user_service.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maha.ecommerce.user_service.security.models.AuthorizationConsent;

@Repository
public interface AuthorizationConsentRepository
		extends JpaRepository<AuthorizationConsent, AuthorizationConsent.AuthorizationConsentId> {
	Optional<AuthorizationConsent> findByRegisteredClientIdAndPrincipalName(String registeredClientId,
			String principalName);

	void deleteByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);
}
