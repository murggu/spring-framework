package com.aitormurguzur.spring.data.redis;

import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

@Component("redisZSetOperations")
public class RedisZSetOperations extends BasicOperations<Object> {

	public void addToZSet(String key, Object value, Double score) {
		redisTemplate.boundZSetOps(key).add(value, score);
	}
	
	public long removeFromSet(String key, Object... values) {
		return redisTemplate.boundZSetOps(key).remove(values);
	}
	
	public void removeRange(String key, Long start, Long end) {
		redisTemplate.boundZSetOps(key).removeRange(start, end);
	}
	
	public void removeByScore(String key, Double min, Double max) {
		redisTemplate.boundZSetOps(key).removeRangeByScore(min, max);
	}
	
	public Set<Object> rangeSet(String key, Long start, Long end) {
		return redisTemplate.boundZSetOps(key).range(start, end);
	}
	
	public Set<Object> rangeByScore(String key, Double min, Double max) {
		return redisTemplate.boundZSetOps(key).rangeByScore(min, max);
	}
	
	public Set<TypedTuple<Object>> rangeByScoreWithScores(String key, Double min, Double max) {
		return redisTemplate.boundZSetOps(key).rangeByScoreWithScores(min, max);
	}
	
	public Set<TypedTuple<Object>> rangeWithScores(String key, Long start, Long end) {
		return redisTemplate.boundZSetOps(key).rangeWithScores(start, end);
	}
	
	public long rank(String key, Object value) {
		return redisTemplate.boundZSetOps(key).rank(value);
	}
	
	public Set<Object> reverseRange(String key, Long start, Long end) {
		return redisTemplate.boundZSetOps(key).reverseRange(start, end);
	}
	
	public Set<Object> reverseRangeByScore(String key, Double min, Double max) {
		return redisTemplate.boundZSetOps(key).reverseRangeByScore(min, max);
	}
	
	public Set<TypedTuple<Object>> reverseRangeByScoreWithScores(String key, Double min, Double max) {
		return redisTemplate.boundZSetOps(key).reverseRangeByScoreWithScores(min, max);
	}
	
	public Set<TypedTuple<Object>> reverseRangeByScore(String key, Long start, Long end) {
		return redisTemplate.boundZSetOps(key).reverseRangeWithScores(start, end);
	}
	
	public long reverseRange(String key, Object value) {
		return redisTemplate.boundZSetOps(key).reverseRank(value);
	}
	
	public double score(String key, Object value) {
		return redisTemplate.boundZSetOps(key).score(value);
	}
	
	public long getLength(String key) {
		return redisTemplate.boundZSetOps(key).size();
	}
}
