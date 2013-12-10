package com.aitormurguzur.spring.data.events.menu;

import com.aitormurguzur.spring.data.events.RequestReadEvent;

public class RequestMenuItemDetailsEvent extends RequestReadEvent {
	
	private String id;

	public RequestMenuItemDetailsEvent(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
