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
public class TestHashOperations extends TestCommonOperations {

	private static Logger logger = LoggerFactory.getLogger(TestHashOperations.class);
	private String key = "friends";
	
	@Test
	public void testHashOperations() {
		hashOperations.hashPut(key, "tom", "jerry");
		assertEquals(1, hashOperations.getLength(key));
		assertEquals("jerry", hashOperations.getValues(key).get(0));
		logger.debug("friends:{}", hashOperations.getEntries(key));
		
		hashOperations.deleteEntries(key, "tom");
		hashOperations.hashPutIfAbsent(key, "tom", "jerry");
		assertEquals(1, hashOperations.getLength(key));
		logger.debug("friends:{}", hashOperations.getEntries(key));
	}
}
