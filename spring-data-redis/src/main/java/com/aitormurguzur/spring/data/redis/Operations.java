package com.aitormurguzur.spring.data.redis;

public interface Operations<K,V> {
	
	public void setKey(K key, V value);
	public boolean setKeyIfAbsent(K key, V value);
	public void setKeyWithExpire(K key, V value, long ttlInMillis);
	public Object getKey(K key);
	public boolean setExpire(K key, long ttlInMillis);
	public long getExpire(K key);
	public boolean keyExists(K key);
	public void deleteKey(K key);
	public void tearDown();
}
