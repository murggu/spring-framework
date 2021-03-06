package com.aitormurguzur.spring.data.events.orders;

import java.util.UUID;

import com.aitormurguzur.spring.data.events.UpdateEvent;

public class SetOrderPaymentEvent extends UpdateEvent {

	private UUID key;
	private PaymentDetails paymentDetails;

	public SetOrderPaymentEvent(UUID key, PaymentDetails paymentDetails) {
		this.key = key;
		this.paymentDetails = paymentDetails;
	}

	public UUID getKey() {
		return key;
	}

	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}
}
