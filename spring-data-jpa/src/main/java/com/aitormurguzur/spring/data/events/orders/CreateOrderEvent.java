package com.aitormurguzur.spring.data.events.orders;

import com.aitormurguzur.spring.data.events.CreateEvent;

public class CreateOrderEvent extends CreateEvent {
	
	private OrderDetails details;

	public CreateOrderEvent(OrderDetails details) {
		this.details = details;
	}

	public OrderDetails getDetails() {
		return details;
	}
}
