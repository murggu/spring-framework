package com.aitormurguzur.spring.data.redis;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "/spring/app-config.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSetOperations extends TestCommonOperations {

	private static Logger logger = LoggerFactory.getLogger(TestSetOperations.class);
	private String keyA = "superpowers";
	private String keyB = "birdpowers";
	
	@Test
	public void testSetOperations() {
		setOperations.addToSet(keyA, "flight", "x-ray vision", "reflexes");
		assertEquals(3, setOperations.getLength(keyA));
		logger.debug("superpowers:{}", setOperations.getMembers(keyA));
		
		setOperations.removeFromSet(keyA, "reflexes");
		assertEquals(2, setOperations.getLength(keyA));
		logger.debug("superpowers:{}", setOperations.getMembers(keyA));
		
		assertTrue(setOperations.isMember(keyA, "flight"));
		assertFalse(setOperations.isMember(keyA, "reflexes"));
		
		setOperations.addToSet(keyB, "pecking", "flight");
		assertEquals(2, setOperations.getLength(keyB));
		logger.debug("birdpowers:{}", setOperations.getMembers(keyB));
		
		List<String> keys = new ArrayList<String>();
		keys.add(keyA);
		keys.add(keyB);
		logger.debug("superpowers:{}", setOperations.combineSets(keyA, keys));
	}
}
