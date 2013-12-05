package com.aitormurguzur.spring.data.redis;

import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * List vs. Set. Set does not have a specific order and each element may only appear once
 * @author amurguzur
 *
 */
@Component("redisSetOperations")
public class RedisSetOperations extends BasicOperations<Object> {

	public long addToSet(String key, Object... values) {
		return redisTemplate.boundSetOps(key).add(values);
	}
	
	public long removeFromSet(String key, Object... values) {
		return redisTemplate.boundSetOps(key).remove(values);
	}

	public boolean isMember(String key, Object value) {
		return redisTemplate.boundSetOps(key).isMember(value);
	}
	
	public Set<Object> getMembers(String key) {
		return redisTemplate.boundSetOps(key).members();
	}
	
	public Set<Object> combineSets(String keyA, String keyB) {
		return redisTemplate.boundSetOps(keyA).union(keyB);
	}
	
	public Set<Object> intersectSets(String keyA, String keyB) {
		return redisTemplate.boundSetOps(keyA).intersect(keyB);
	}
	
	public long getLength(String key) {
		return redisTemplate.boundSetOps(key).size();
	}
}
