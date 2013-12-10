package com.aitormurguzur.spring.data.events.orders;

import java.util.UUID;

import com.aitormurguzur.spring.data.events.RequestReadEvent;

public class RequestOrderStatusEvent extends RequestReadEvent {
	
	private UUID key;

	public RequestOrderStatusEvent(UUID key) {
		this.key = key;
	}

	public UUID getKey() {
		return key;
	}
}
