package com.aitormurguzur.spring.data.core.services;

import com.aitormurguzur.spring.data.events.orders.OrderStatusEvent;
import com.aitormurguzur.spring.data.events.orders.SetOrderStatusEvent;

public interface OrderStatusUpdateService {

	OrderStatusEvent setOrderStatus(SetOrderStatusEvent orderStatusEvent);
}
