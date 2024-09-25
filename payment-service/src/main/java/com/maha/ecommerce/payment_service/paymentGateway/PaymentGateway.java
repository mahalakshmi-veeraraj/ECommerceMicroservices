package com.maha.ecommerce.payment_service.paymentGateway;

import com.razorpay.RazorpayException;

public interface PaymentGateway {
	String generatePaymentLink(Long id, Long amount) throws RazorpayException;
}
