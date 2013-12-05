package com.aitormurguzur.spring.data.redis;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "/spring/app-config.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestValueOperations extends TestCommonOperations {

	private static Logger logger = LoggerFactory.getLogger(TestValueOperations.class);
	
	@Test
	public void testValueOperations() {
		valueOperations.setKey("connections", 10L);
		assertEquals(10, (long) valueOperations.getKey("connections"));
		assertEquals(true, valueOperations.keyExists("connections"));
		logger.debug("connections = {}", valueOperations.getKey("connections"));
		
		assertEquals(11, (long) valueOperations.incrementKey("connections", (long) 1));
	}
}
