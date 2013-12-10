package com.aitormurguzur.spring.data.persistence.service;

import com.aitormurguzur.spring.data.events.orders.*;
import com.aitormurguzur.spring.data.persistence.domain.*;
import com.aitormurguzur.spring.data.persistence.repository.gemfire.OrderStatusRepository;
import com.aitormurguzur.spring.data.persistence.repository.postgresql.OrdersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderPersistenceEventHandler implements OrderPersistenceService {

	private final OrdersRepository orderRepository;
	private final OrderStatusRepository orderStatusRepository;

	public OrderPersistenceEventHandler(final OrdersRepository orderRepository,
			final OrderStatusRepository orderStatusRepository) {
		this.orderRepository = orderRepository;
		this.orderStatusRepository = orderStatusRepository;
	}

	public OrderStatusEvent setOrderStatus(SetOrderStatusEvent event) {
		OrderStatus status = OrderStatus.fromStatusDetails(event.getOrderStatus());
		status = orderStatusRepository.save(status);
		return new OrderStatusEvent(status.getId(), status.toStatusDetails());
	}

	public OrderCreatedEvent createOrder(CreateOrderEvent createOrderEvent) {
		Order order = Order.fromOrderDetails(createOrderEvent.getDetails());
		order = orderRepository.save(order);
		return new OrderCreatedEvent(UUID.fromString(order.getId()), order.toOrderDetails());
	}

	public AllOrdersEvent requestAllOrders(RequestAllOrdersEvent requestAllCurrentOrdersEvent) {
		List<OrderDetails> generatedDetails = new ArrayList<OrderDetails>();
		for (Order order : orderRepository.findAll()) {
			generatedDetails.add(order.toOrderDetails());
		}
		return new AllOrdersEvent(generatedDetails);
	}

	public OrderDetailsEvent requestOrderDetails(RequestOrderDetailsEvent requestOrderDetailsEvent) {
		Order order = orderRepository.findOne(requestOrderDetailsEvent.getKey().toString());
		if (order == null) {
			return OrderDetailsEvent.notFound(requestOrderDetailsEvent.getKey());
		}
		return new OrderDetailsEvent(requestOrderDetailsEvent.getKey(), order.toOrderDetails());
	}

	public OrderUpdatedEvent setOrderPayment(SetOrderPaymentEvent setOrderPaymentEvent) {
		Order order = orderRepository.findOne(setOrderPaymentEvent.getKey().toString());
		if (order == null) {
			return OrderUpdatedEvent.notFound(setOrderPaymentEvent.getKey());
		}
		// TODO, handling payment details...
		return new OrderUpdatedEvent(UUID.fromString(order.getId()),
				order.toOrderDetails());
	}

	public OrderDeletedEvent deleteOrder(DeleteOrderEvent deleteOrderEvent) {
		Order order = orderRepository.findOne(deleteOrderEvent.getKey().toString());
		if (order == null) {
			return OrderDeletedEvent.notFound(deleteOrderEvent.getKey());
		}
		orderRepository.delete(deleteOrderEvent.getKey().toString());
		return new OrderDeletedEvent(deleteOrderEvent.getKey(), order.toOrderDetails());
	}

	public OrderStatusEvent requestOrderStatus(RequestOrderStatusEvent requestOrderDetailsEvent) {
		OrderStatus status = orderStatusRepository.findOne(requestOrderDetailsEvent.getKey());
		if (status == null) {
			return OrderStatusEvent.notFound(requestOrderDetailsEvent.getKey());
		}
		return new OrderStatusEvent(requestOrderDetailsEvent.getKey(), status.toStatusDetails());
	}
}
