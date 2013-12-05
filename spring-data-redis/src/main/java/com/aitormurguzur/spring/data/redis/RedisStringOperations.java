package com.aitormurguzur.spring.data.redis;

import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

@Component("redisStringOperations")
public class RedisStringOperations extends BasicOperations<String> {
	
	public void setKey(String key, String value) {
		redisTemplate.boundValueOps(key).set(value);
	}
	
	public boolean setKeyIfAbsent(String key, String value) {
		return redisTemplate.boundValueOps(key).setIfAbsent(value);
	}
	
	public void setKeyWithExpire(String key, String value, long ttlInMillis) {
		redisTemplate.boundValueOps(key).set(value, ttlInMillis, TimeUnit.MILLISECONDS);
	}
	
	public String getKey(String key) {
		return redisTemplate.boundValueOps(key).get();
	}
}
