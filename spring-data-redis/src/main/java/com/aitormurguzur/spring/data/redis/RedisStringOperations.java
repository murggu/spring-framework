package com.aitormurguzur.spring.data.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component("redisStringOperations")
public class RedisStringOperations {
		
	// inject the string redis template
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	public void setKey(String key, String value) {
		stringRedisTemplate.boundValueOps(key).set(value);
	}
	
	public boolean setKeyIfAbsent(String key, String value) {
		return stringRedisTemplate.boundValueOps(key).setIfAbsent(value);
	}
	
	public void setKeyWithExpire(String key, String value, long ttlInMs) {
		stringRedisTemplate.boundValueOps(key).set(value, ttlInMs, TimeUnit.MILLISECONDS);
	}
	
	public String getKey(String key) {
		return stringRedisTemplate.boundValueOps(key).get();
	}
	
	public boolean setExpire(String key, long ttlInMs) {
		return stringRedisTemplate.boundValueOps(key).expire(ttlInMs, TimeUnit.MILLISECONDS);
	}
	
	public long getExpire(String key) {
		return stringRedisTemplate.boundValueOps(key).getExpire();
	}
	
	public boolean keyExists(String key) {
		return stringRedisTemplate.hasKey(key);
	}
	
	public void deleteKey(String key) {
		stringRedisTemplate.delete(key);
	}
}
