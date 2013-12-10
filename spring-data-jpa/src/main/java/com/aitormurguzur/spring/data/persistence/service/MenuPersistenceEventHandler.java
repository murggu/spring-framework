package com.aitormurguzur.spring.data.persistence.service;

import java.util.ArrayList;
import java.util.List;

import com.aitormurguzur.spring.data.events.menu.AllMenuItemsEvent;
import com.aitormurguzur.spring.data.events.menu.CreateMenuItemEvent;
import com.aitormurguzur.spring.data.events.menu.MenuItemDetails;
import com.aitormurguzur.spring.data.events.menu.MenuItemDetailsEvent;
import com.aitormurguzur.spring.data.events.menu.RequestAllMenuItemsEvent;
import com.aitormurguzur.spring.data.events.menu.RequestMenuItemDetailsEvent;
import com.aitormurguzur.spring.data.persistence.domain.MenuItem;
import com.aitormurguzur.spring.data.persistence.repository.mongo.MenuItemRepository;

public class MenuPersistenceEventHandler implements MenuPersistenceService {

	private MenuItemRepository menuItemRepository;

	public MenuPersistenceEventHandler(MenuItemRepository menuItemRepository) {
		this.menuItemRepository = menuItemRepository;
	}

	public AllMenuItemsEvent requestAllMenuItems(RequestAllMenuItemsEvent requestAllMenuItemsEvent) {
		Iterable<MenuItem> menuItems = menuItemRepository.findAll();
		List<MenuItemDetails> details = new ArrayList<MenuItemDetails>();

		for (MenuItem item : menuItems) {
			details.add(item.toStatusDetails());
		}
		return new AllMenuItemsEvent(details);
	}

	public MenuItemDetailsEvent requestMenuItemDetails(RequestMenuItemDetailsEvent requestMenuItemDetailsEvent) {
		MenuItem item = menuItemRepository.findOne(requestMenuItemDetailsEvent.getId());

		if (item == null) {
			return MenuItemDetailsEvent.notFound(requestMenuItemDetailsEvent.getId());
		}
		return new MenuItemDetailsEvent(requestMenuItemDetailsEvent.getId(), item.toStatusDetails());
	}

	public MenuItemDetailsEvent createMenuItem(CreateMenuItemEvent createMenuItemEvent) {
		MenuItem item = menuItemRepository.save(MenuItem.fromStatusDetails(createMenuItemEvent.getDetails()));
		return new MenuItemDetailsEvent(item.getId(), item.toStatusDetails());
	}
}
