package com.maha.ecommerce.payment_service.paymentGateway;

import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway{

	@Override
	public String generatePaymentLink(Long id, Long amount) {
		// Call the Stripe API to generate the payment link.
		
		return null;
	}

}
