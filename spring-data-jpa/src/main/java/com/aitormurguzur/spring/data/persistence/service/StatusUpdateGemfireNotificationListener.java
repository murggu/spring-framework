package com.aitormurguzur.spring.data.persistence.service;

import com.gemstone.gemfire.cache.query.CqEvent;
import com.aitormurguzur.spring.data.core.services.OrderStatusUpdateService;
import com.aitormurguzur.spring.data.events.orders.SetOrderStatusEvent;
import com.aitormurguzur.spring.data.persistence.domain.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class StatusUpdateGemfireNotificationListener {

	@Autowired
	private OrderStatusUpdateService orderStatusUpdateService;

	public void setOrderStatusUpdateService(OrderStatusUpdateService orderStatusUpdateService) {
		this.orderStatusUpdateService = orderStatusUpdateService;
	}

	public void handleEvent(CqEvent event) {
		if (!event.getBaseOperation().isCreate()) {
			return;
		}
		OrderStatus status = (OrderStatus) event.getNewValue();
		orderStatusUpdateService.setOrderStatus(new SetOrderStatusEvent(status.getOrderId(), status.toStatusDetails()));
	}
}