package com.aitormurguzur.spring.data.persistence.repository.gemfire;

import org.springframework.data.gemfire.repository.GemfireRepository;
import org.springframework.data.gemfire.repository.Query;

import com.aitormurguzur.spring.data.persistence.domain.OrderStatus;

import java.util.Collection;
import java.util.UUID;

public interface OrderStatusRepository extends GemfireRepository<OrderStatus, UUID> {

	@Query("SELECT DISTINCT * FROM /YummyNoodleOrder WHERE orderId = $1 ORDER BY statusDate")
	public Collection<OrderStatus> getOrderHistory(UUID orderId);
}