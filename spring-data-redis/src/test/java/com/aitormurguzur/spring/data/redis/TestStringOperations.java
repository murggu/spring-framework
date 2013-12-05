package com.aitormurguzur.spring.data.redis;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "/spring/app-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestStringOperations {
	
	private static Logger logger = LoggerFactory.getLogger(TestStringOperations.class);
	
	@Autowired
	private RedisStringOperations stringOperations;
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testBasicStringOperation() throws InterruptedException {
		stringOperations.setKey("server:name", "duck");
		assertEquals("duck", stringOperations.getKey("server:name"));
		assertEquals(true, stringOperations.keyExists("server:name"));
		logger.debug("server:name = {}", stringOperations.getKey("server:name"));
		
		stringOperations.deleteKey("server:name");
		assertEquals(false, stringOperations.keyExists("server:name"));
		
		stringOperations.setKeyIfAbsent("server:name", "bird");
		assertEquals("bird", stringOperations.getKey("server:name"));
		assertEquals(true, stringOperations.keyExists("server:name"));
		logger.debug("server:name = {}", stringOperations.getKey("server:name"));
		
		stringOperations.setExpire("server:name", 1000);
		logger.debug("set ttl:{}", stringOperations.getExpire("server:name"));
		Thread.sleep(1000);
		assertEquals(false, stringOperations.keyExists("server:name"));
		
		stringOperations.setKeyWithExpire("server:name", "lion", 2000);
		assertEquals("lion", stringOperations.getKey("server:name"));
		assertEquals(true, stringOperations.keyExists("server:name"));
		
		logger.debug("set ttl:{}", stringOperations.getExpire("server:name"));
		logger.debug("server:name = {}", stringOperations.getKey("server:name"));
		Thread.sleep(2000);
		assertEquals(false, stringOperations.keyExists("server:name"));
	}
}
