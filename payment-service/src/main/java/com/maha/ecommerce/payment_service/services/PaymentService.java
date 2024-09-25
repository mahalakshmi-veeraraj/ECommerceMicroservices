package com.maha.ecommerce.payment_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.maha.ecommerce.payment_service.paymentGateway.PaymentGateway;
import com.razorpay.RazorpayException;

@Service
public class PaymentService {
	@Autowired
	@Qualifier("razorpayPaymentGateway")
	private PaymentGateway paymentGateway;

	public String initiatePayment(Long orderId, Long orderAmount) throws RazorpayException {
		// Make a call to Payment Gateway to generate the payment link.
		try {
			return paymentGateway.generatePaymentLink(orderId, orderAmount);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
