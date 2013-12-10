package com.aitormurguzur.spring.data.persistence.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.Region;

import com.aitormurguzur.spring.data.events.orders.OrderStatusDetails;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

// {!begin gemfire}
@Region("YummyNoodleOrder")
public class OrderStatus implements Serializable {

	private static final long serialVersionUID = -4268432271733125946L;
	private UUID orderId;
	@Id
	private UUID id;
	// {!end gemfire}
	private Date statusDate;
	private String status;

	public OrderStatus(UUID orderId, UUID id, final Date date,
			final String status) {
		this.orderId = orderId;
		this.id = id;
		this.status = status;
		this.statusDate = date;
	}

	public OrderStatus() {

	}

	public Date getStatusDate() {
		return statusDate;
	}

	public String getStatus() {
		return status;
	}

	public UUID getOrderId() {
		return orderId;
	}

	public UUID getId() {
		return id;
	}

	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OrderStatusDetails toStatusDetails() {
		return new OrderStatusDetails(orderId, id, statusDate, status);
	}

	public static OrderStatus fromStatusDetails(
			OrderStatusDetails orderStatusDetails) {
		return new OrderStatus(orderStatusDetails.getOrderId(),
				orderStatusDetails.getId(), orderStatusDetails.getStatusDate(),
				orderStatusDetails.getStatus());
	}
}
