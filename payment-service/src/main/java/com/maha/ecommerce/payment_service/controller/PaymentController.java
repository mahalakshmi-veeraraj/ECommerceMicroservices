package com.maha.ecommerce.payment_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maha.ecommerce.payment_service.dto.InitiatePaymentRequestDto;
import com.maha.ecommerce.payment_service.services.PaymentService;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/initiatePayment")
	public String inititatePayment(@RequestBody InitiatePaymentRequestDto requestDto) throws RazorpayException {

		return paymentService.initiatePayment(requestDto.getOrderId(), requestDto.getAmount());
	}
}
