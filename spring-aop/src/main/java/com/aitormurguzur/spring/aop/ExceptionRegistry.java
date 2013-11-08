package com.aitormurguzur.spring.aop;

import java.util.HashMap;
import java.util.Map;

public class ExceptionRegistry {

	static Map<String, Exception> _receivedExceptions = new HashMap<String, Exception>();
	
	public static void registerException(String method, Exception exception) {
		_receivedExceptions.put(method, exception);
		// do something, e.g., send notifications
	}
	
	public static int getReceivedExceptionsNumber() {
		return _receivedExceptions.size();
	}
}
