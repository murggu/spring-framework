package com.aitormurguzur.spring.data.redis;

import java.util.List;

import org.springframework.stereotype.Component;

@Component("redisListOperations")
public class RedisListOperations extends BasicOperations<Object> {

	public long rightPush(String key, Object value) {
		return redisTemplate.boundListOps(key).rightPush(value);
	}
	
	public long leftPush(String key, Object value) {
		return redisTemplate.boundListOps(key).leftPush(value);
	}
	
	public Object rightPop(String key) {
		return redisTemplate.boundListOps(key).rightPop();
	}
	
	public Object leftPop(String key) {
		return redisTemplate.boundListOps(key).leftPop();
	}

	public List<Object> leftRange(String key, long start, long end) {
		return redisTemplate.boundListOps(key).range(start, end);
	}
	
	public void trimList(String key, long start, long end) {
		redisTemplate.boundListOps(key).trim(start, end);
	}
	
	public long getLength(String key) {
		return redisTemplate.boundListOps(key).size();
	}
}
