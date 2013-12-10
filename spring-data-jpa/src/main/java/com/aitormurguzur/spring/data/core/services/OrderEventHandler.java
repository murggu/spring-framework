package com.aitormurguzur.spring.data.core.services;

import java.util.Date;
import java.util.UUID;

import com.aitormurguzur.spring.data.core.domain.Order;
import com.aitormurguzur.spring.data.events.orders.*;
import com.aitormurguzur.spring.data.persistence.service.OrderPersistenceService;

public class OrderEventHandler implements OrderService {

	private final OrderPersistenceService ordersPersistenceService;

	public OrderEventHandler(final OrderPersistenceService ordersPersistenceService) {
		this.ordersPersistenceService = ordersPersistenceService;
	}

	public OrderCreatedEvent createOrder(CreateOrderEvent createOrderEvent) {
		Order order = Order.fromOrderDetails(createOrderEvent.getDetails());
		OrderCreatedEvent event = ordersPersistenceService.createOrder(createOrderEvent);
		@SuppressWarnings("unused")
		OrderStatusEvent orderStatusEvent = ordersPersistenceService
				.setOrderStatus(new SetOrderStatusEvent(order.getKey(),
						new OrderStatusDetails(order.getKey(), UUID
								.randomUUID(), new Date(), "Order Created")));

		return event;
	}

	public AllOrdersEvent requestAllOrders(RequestAllOrdersEvent requestAllCurrentOrdersEvent) {
		return ordersPersistenceService
				.requestAllOrders(requestAllCurrentOrdersEvent);
	}

	public OrderDetailsEvent requestOrderDetails(RequestOrderDetailsEvent requestOrderDetailsEvent) {
		return ordersPersistenceService
				.requestOrderDetails(requestOrderDetailsEvent);
	}

	public OrderUpdatedEvent setOrderPayment(SetOrderPaymentEvent setOrderPaymentEvent) {
		return ordersPersistenceService.setOrderPayment(setOrderPaymentEvent);
	}

	public OrderDeletedEvent deleteOrder(DeleteOrderEvent deleteOrderEvent) {
		OrderDetailsEvent orderDetailsEvent = ordersPersistenceService
				.requestOrderDetails(new RequestOrderDetailsEvent(
						deleteOrderEvent.getKey()));

		if (!orderDetailsEvent.isEntityFound()) {
			return OrderDeletedEvent.notFound(deleteOrderEvent.getKey());
		}

		Order order = Order.fromOrderDetails(orderDetailsEvent.getOrderDetails());

		if (!order.canBeDeleted()) {
			return OrderDeletedEvent.deletionForbidden(
					deleteOrderEvent.getKey(), order.toOrderDetails());
		}
		ordersPersistenceService.deleteOrder(deleteOrderEvent);

		return new OrderDeletedEvent(deleteOrderEvent.getKey(), order.toOrderDetails());
	}

	public OrderStatusEvent requestOrderStatus(RequestOrderStatusEvent requestOrderDetailsEvent) {
		return ordersPersistenceService.requestOrderStatus(requestOrderDetailsEvent);
	}
}
