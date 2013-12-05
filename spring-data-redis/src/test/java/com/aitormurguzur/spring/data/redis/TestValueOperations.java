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
	private String key = "connections";
	
	@Test
	public void testValueOperations() {
		valueOperations.setKey(key, 10L);
		assertEquals(10, (long) valueOperations.getKey(key));
		assertEquals(true, valueOperations.keyExists(key));
		logger.debug("connections = {}", valueOperations.getKey(key));
		
		assertEquals(11, (long) valueOperations.incrementKey(key, (long) 1));
	}
}
