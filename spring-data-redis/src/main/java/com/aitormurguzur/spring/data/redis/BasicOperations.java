package com.aitormurguzur.spring.data.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

public abstract class BasicOperations<V> implements Operations<String, V> {

	@Autowired
	protected RedisTemplate<String, V> redisTemplate;
	
	public boolean keyExists(String key) {
		return redisTemplate.hasKey(key);
	}
	
	public void deleteKey(String key) {
		redisTemplate.delete(key);
	}
	
	public boolean setExpire(String key, long ttlInMillis) {
		return redisTemplate.expire(key, ttlInMillis, TimeUnit.MILLISECONDS);
	}
	
	public long getExpire(String key) {
		return redisTemplate.getExpire(key);
	}
	
	public void tearDown() {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) {
				connection.flushDb();
				return null;
			}
		});
	}
}
