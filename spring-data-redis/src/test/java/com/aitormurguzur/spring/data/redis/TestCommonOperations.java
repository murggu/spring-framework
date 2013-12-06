package com.aitormurguzur.spring.data.redis;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class TestCommonOperations {

	@Autowired
	protected RedisStringOperations stringOperations;
	
	@Autowired
	protected RedisValueOperations valueOperations;
	
	@Autowired
	protected RedisListOperations listOperations;
	
	@Autowired
	protected RedisSetOperations setOperations;
	
	@Autowired
	protected RedisHashOperations hashOperations;
	
	@Autowired
	protected RedisZSetOperations zSetOperations;

	@Before
	public void tearDown() {
		valueOperations.tearDown();
	}
}
