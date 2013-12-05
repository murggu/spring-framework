package com.aitormurguzur.spring.data.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.stereotype.Component;

@Component("redisValueOperations")
public class RedisValueOperations extends BasicOperations<Long> {
	
	public void setKey(String key, Long value) {
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Long>(Long.class));
		redisTemplate.boundValueOps(key).set(value);
	}

	public boolean setKeyIfAbsent(String key, Long value) {
		return redisTemplate.boundValueOps(key).setIfAbsent(value);
	}

	public void setKeyWithExpire(String key, Long value, long ttlInMillis) {
		redisTemplate.boundValueOps(key).set(value, ttlInMillis, TimeUnit.MILLISECONDS);
	}
	
	public Long incrementKey(String key, Long incValue) {
		return redisTemplate.boundValueOps(key).increment(incValue);
	}

	public Long getKey(String key) {
		return redisTemplate.boundValueOps(key).get();
	}
}
