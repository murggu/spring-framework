package com.aitormurguzur.spring.data.persistence.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.aitormurguzur.spring.data.persistence.domain.fixture.JPAAssertions.assertTableExists;
import static com.aitormurguzur.spring.data.persistence.domain.fixture.JPAAssertions.assertTableHasColumn;

@ContextConfiguration(locations = { "/spring/app-config.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OrderMappingIntegrationTests {

	@Autowired
	EntityManager entityManager;

	@Test
	public void thatItemCustomMappingWorks() throws Exception {
		assertTableExists(entityManager, "NOODLE_ORDERS");
		assertTableExists(entityManager, "ORDER_ORDER_ITEMS");
		assertTableHasColumn(entityManager, "NOODLE_ORDERS", "ORDER_ID");
		assertTableHasColumn(entityManager, "NOODLE_ORDERS", "SUBMISSION_DATETIME");
	}
}