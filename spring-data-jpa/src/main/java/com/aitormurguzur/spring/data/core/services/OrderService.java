package com.aitormurguzur.spring.data.core.services;

import com.aitormurguzur.spring.data.events.orders.*;

public interface OrderService {

	public AllOrdersEvent requestAllOrders(RequestAllOrdersEvent requestAllCurrentOrdersEvent);
	public OrderDetailsEvent requestOrderDetails(RequestOrderDetailsEvent requestOrderDetailsEvent);
	public OrderStatusEvent requestOrderStatus(RequestOrderStatusEvent requestOrderStatusEvent);
	public OrderCreatedEvent createOrder(CreateOrderEvent event);
	public OrderUpdatedEvent setOrderPayment(SetOrderPaymentEvent setOrderPaymentEvent);
	public OrderDeletedEvent deleteOrder(DeleteOrderEvent deleteOrderEvent);
}
