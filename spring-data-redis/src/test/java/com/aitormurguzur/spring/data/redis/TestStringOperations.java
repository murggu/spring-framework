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
public class TestStringOperations extends TestCommonOperations {

	private static Logger logger = LoggerFactory.getLogger(TestStringOperations.class);
	private String key = "server:name";
	
	@Test
	public void testStringOperations() throws InterruptedException {
		stringOperations.setKey(key, "duck");
		assertEquals("duck", stringOperations.getKey(key));
		assertEquals(true, stringOperations.keyExists(key));
		logger.debug("server:name = {}", stringOperations.getKey(key));

		stringOperations.deleteKey(key);
		assertEquals(false, stringOperations.keyExists(key));

		stringOperations.setKeyIfAbsent(key, "bird");
		assertEquals("bird", stringOperations.getKey(key));
		assertEquals(true, stringOperations.keyExists(key));
		logger.debug("server:name = {}", stringOperations.getKey(key));

		stringOperations.setExpire(key, 1000);
		logger.debug("set ttl:{}", stringOperations.getExpire(key));
		Thread.sleep(1000);
		assertEquals(false, stringOperations.keyExists(key));

		stringOperations.setKeyWithExpire(key, "lion", 2000);
		assertEquals("lion", stringOperations.getKey(key));
		assertEquals(true, stringOperations.keyExists(key));

		logger.debug("set ttl:{}", stringOperations.getExpire(key));
		logger.debug("server:name = {}", stringOperations.getKey(key));
		Thread.sleep(2000);
		assertEquals(false, stringOperations.keyExists(key));
	}
}
