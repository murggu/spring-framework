package com.aitormurguzur.spring.data.persistence.service;

import com.aitormurguzur.spring.data.events.orders.*;

public interface OrderPersistenceService {

	public AllOrdersEvent requestAllOrders(RequestAllOrdersEvent requestAllCurrentOrdersEvent);
	public OrderDetailsEvent requestOrderDetails(RequestOrderDetailsEvent requestOrderDetailsEvent);
	public OrderStatusEvent requestOrderStatus(RequestOrderStatusEvent requestOrderStatusEvent);
	public OrderCreatedEvent createOrder(CreateOrderEvent event);
	public OrderStatusEvent setOrderStatus(SetOrderStatusEvent event);
	public OrderUpdatedEvent setOrderPayment(SetOrderPaymentEvent setOrderPaymentEvent);
	public OrderDeletedEvent deleteOrder(DeleteOrderEvent deleteOrderEvent);
}
