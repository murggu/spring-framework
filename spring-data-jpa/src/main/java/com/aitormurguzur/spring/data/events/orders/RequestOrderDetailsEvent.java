package com.aitormurguzur.spring.data.events.orders;

import java.util.UUID;

import com.aitormurguzur.spring.data.events.RequestReadEvent;

public class RequestOrderDetailsEvent extends RequestReadEvent {
	
	private UUID key;

	public RequestOrderDetailsEvent(UUID key) {
		this.key = key;
	}

	public UUID getKey() {
		return key;
	}
}
