package com.aitormurguzur.spring.data.events.menu;

import com.aitormurguzur.spring.data.events.CreateEvent;

public class CreateMenuItemEvent extends CreateEvent {

	private MenuItemDetails details;

	public CreateMenuItemEvent(MenuItemDetails details) {
		this.details = details;
	}

	public MenuItemDetails getDetails() {
		return details;
	}
}
