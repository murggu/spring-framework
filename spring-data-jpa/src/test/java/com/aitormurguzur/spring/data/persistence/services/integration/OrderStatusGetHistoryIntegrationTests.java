package com.aitormurguzur.spring.data.persistence.services.integration;

import com.aitormurguzur.spring.data.persistence.domain.OrderStatus;
import com.aitormurguzur.spring.data.persistence.domain.fixture.PersistenceFixture;
import com.aitormurguzur.spring.data.persistence.repository.gemfire.OrderStatusRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@ContextConfiguration(locations = { "/spring/gemfire-config.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
public class OrderStatusGetHistoryIntegrationTests {

	@Autowired
	OrderStatusRepository ordersStatusRepository;

	@Before
	public void setup() {
		ordersStatusRepository.deleteAll();
	}

	@After
	public void teardown() {
		ordersStatusRepository.deleteAll();
	}

	@Test
	public void thatGetHistoryWorks() throws Exception {

		UUID orderId = UUID.randomUUID();

		UUID key0 = ordersStatusRepository.save(
				PersistenceFixture.orderReceived(orderId)).getId();
		UUID key1 = ordersStatusRepository.save(
				PersistenceFixture.orderReceived(orderId)).getId();
		UUID key2 = ordersStatusRepository.save(
				PersistenceFixture.orderReceived(orderId)).getId();

		List<OrderStatus> history = new ArrayList<OrderStatus>(
				ordersStatusRepository.getOrderHistory(orderId));

		assertNotNull(history);
		assertEquals(3, history.size());
		assertEquals(key0, history.get(0).getId());
		assertEquals(key1, history.get(1).getId());
		assertEquals(key2, history.get(2).getId());
	}
}
