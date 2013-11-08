package com.aitormurguzur.spring.aop;

import org.springframework.stereotype.Component;

@Component("sampleLogic")
public class SampleLogic {

	public void firstMethod() {
		// do something
		throw new RuntimeException("generic exception");
	}
	
	public String secondMethod(){
		// do something
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			throw new RuntimeException("thread interrupt exception");
		}
		return "second method result";
	}
	
	public void thirdMethod(){
		// do something
		throw new RuntimeException("generic exception");
	}
}
