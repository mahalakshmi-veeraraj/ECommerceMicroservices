package com.maha.ecommerce.payment_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentRequestDto {
	private Long orderId;
	private Long amount;
}
