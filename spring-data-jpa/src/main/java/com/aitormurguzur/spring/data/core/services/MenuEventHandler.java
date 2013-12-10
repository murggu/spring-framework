package com.aitormurguzur.spring.data.core.services;

import com.aitormurguzur.spring.data.events.menu.AllMenuItemsEvent;
import com.aitormurguzur.spring.data.events.menu.CreateMenuItemEvent;
import com.aitormurguzur.spring.data.events.menu.MenuItemDetailsEvent;
import com.aitormurguzur.spring.data.events.menu.RequestAllMenuItemsEvent;
import com.aitormurguzur.spring.data.events.menu.RequestMenuItemDetailsEvent;
import com.aitormurguzur.spring.data.persistence.service.MenuPersistenceService;

public class MenuEventHandler implements MenuService {

	private MenuPersistenceService menuPersistenceService;

	public MenuEventHandler(MenuPersistenceService menuPersistenceService) {
		this.menuPersistenceService = menuPersistenceService;
	}

	public AllMenuItemsEvent requestAllMenuItems(
			RequestAllMenuItemsEvent requestAllMenuItemsEvent) {
		return menuPersistenceService
				.requestAllMenuItems(requestAllMenuItemsEvent);
	}

	public MenuItemDetailsEvent requestMenuItemDetails(RequestMenuItemDetailsEvent requestMenuItemDetailsEvent) {
		return menuPersistenceService
				.requestMenuItemDetails(requestMenuItemDetailsEvent);
	}

	public MenuItemDetailsEvent createMenuItem(CreateMenuItemEvent createMenuItemEvent) {
		return menuPersistenceService.createMenuItem(createMenuItemEvent);
	}
}
