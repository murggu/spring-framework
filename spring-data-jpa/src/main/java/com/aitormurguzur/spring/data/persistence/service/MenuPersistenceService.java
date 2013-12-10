package com.aitormurguzur.spring.data.persistence.service;

import com.aitormurguzur.spring.data.events.menu.AllMenuItemsEvent;
import com.aitormurguzur.spring.data.events.menu.CreateMenuItemEvent;
import com.aitormurguzur.spring.data.events.menu.MenuItemDetailsEvent;
import com.aitormurguzur.spring.data.events.menu.RequestAllMenuItemsEvent;
import com.aitormurguzur.spring.data.events.menu.RequestMenuItemDetailsEvent;

public interface MenuPersistenceService {

	AllMenuItemsEvent requestAllMenuItems(RequestAllMenuItemsEvent requestAllMenuItemsEvent);
	MenuItemDetailsEvent requestMenuItemDetails(RequestMenuItemDetailsEvent requestMenuItemDetailsEvent);
	MenuItemDetailsEvent createMenuItem(CreateMenuItemEvent createMenuItemEvent);
}
