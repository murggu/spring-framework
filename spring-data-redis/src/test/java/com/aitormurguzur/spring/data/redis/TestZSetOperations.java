package com.aitormurguzur.spring.data.redis;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "/spring/app-config.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestZSetOperations extends TestCommonOperations {

	private static Logger logger = LoggerFactory.getLogger(TestZSetOperations.class);
	private String keyA = "superpowers";
	
	@Test
	public void testZSetOperations() {
		zSetOperations.addToZSet(keyA, "flight", 1200D);
		zSetOperations.addToZSet(keyA, "x-ray vision", 1300D);
		zSetOperations.addToZSet(keyA, "reflexes", 1400D);
		assertEquals(3, zSetOperations.getLength(keyA));
		logger.debug("superpowers:{}", zSetOperations.rangeByScore(keyA, 1200D, 1400D));
		
		assertEquals(1, zSetOperations.rank(keyA, "x-ray vision"));
		logger.debug("superpowers:{}", zSetOperations.reverseRangeByScore(keyA, 1200D, 1400D));
		assertEquals(1200D, zSetOperations.score(keyA, "flight"), 0);
	}
}
