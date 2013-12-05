package com.aitormurguzur.spring.data.redis;

public interface Operations<K,V> {
	
	public boolean setExpire(K key, long ttlInMillis);
	public long getExpire(K key);
	public boolean keyExists(K key);
	public void deleteKey(K key);
	public void tearDown();
}
