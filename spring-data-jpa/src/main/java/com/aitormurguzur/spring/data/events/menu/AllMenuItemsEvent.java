package com.aitormurguzur.spring.data.events.menu;

import java.util.List;

import com.aitormurguzur.spring.data.events.ReadEvent;

public class AllMenuItemsEvent extends ReadEvent {
	
	private List<MenuItemDetails> menuItemDetails;

	public AllMenuItemsEvent(List<MenuItemDetails> menuItemDetails) {
		this.menuItemDetails = menuItemDetails;
	}

	public List<MenuItemDetails> getMenuItemDetails() {
		return menuItemDetails;
	}
}
