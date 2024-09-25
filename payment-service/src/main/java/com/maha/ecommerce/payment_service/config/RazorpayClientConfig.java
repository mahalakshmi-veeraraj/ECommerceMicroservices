package com.maha.ecommerce.payment_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Configuration
public class RazorpayClientConfig {
	@Value("razorpay.key.id")
	private String razorPayKeyId;
	@Value("razorpay.key.secret")
	private String razorPayKeySecret;

	@Bean
	public RazorpayClient getRazorpayClient() throws RazorpayException {
		return new RazorpayClient(razorPayKeyId, razorPayKeySecret);
	}
}
