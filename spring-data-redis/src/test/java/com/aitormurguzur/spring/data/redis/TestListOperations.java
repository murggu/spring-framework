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
public class TestListOperations extends TestCommonOperations {

	private static Logger logger = LoggerFactory.getLogger(TestListOperations.class);
	private String key = "friends";
	
	@Test
	public void testListOperations() {
		listOperations.rightPush(key, "Alice");
		listOperations.rightPush(key, "Bob");
		listOperations.leftPush(key, "Sam");
		assertEquals(3, listOperations.getLength(key));
		logger.debug("friends:{}", listOperations.leftRange(key, 0, -1));
		
		listOperations.leftPop(key);
		assertEquals(2, listOperations.getLength(key));
		logger.debug("friends:{}", listOperations.leftRange(key, 0, -1));
		
		listOperations.rightPop(key);
		assertEquals(1, listOperations.getLength(key));
		logger.debug("friends:{}", listOperations.leftRange(key, 0, -1));
	}
}
