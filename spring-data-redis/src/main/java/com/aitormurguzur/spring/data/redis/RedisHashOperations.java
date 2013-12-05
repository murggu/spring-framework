package com.aitormurguzur.spring.data.redis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component("redisHashOperations")
public class RedisHashOperations extends BasicOperations<Object> {

	public void hashPut(String key, Object mapKey, Object value) {
		redisTemplate.boundHashOps(key).put(mapKey, value);
	}
	
	public boolean hashPutIfAbsent(String key, Object mapKey, Object value) {
		return redisTemplate.boundHashOps(key).putIfAbsent(mapKey, value);
	}
	
	public Map<Object,Object> getEntries(String key) {
		return redisTemplate.boundHashOps(key).entries();
	}
	
	public List<Object> getValues(String key) {
		return redisTemplate.boundHashOps(key).values();
	}
	
	public void deleteEntries(String key, Object... mapKeys) {
		redisTemplate.boundHashOps(key).delete(mapKeys);
	}
	
	public long getLength(String key) {
		return redisTemplate.boundHashOps(key).size();
	}
}
