package com.aitormurguzur.spring.jms.tx;

import java.io.Serializable;

public class SampleMessage implements Serializable {
	
	private static final long serialVersionUID = -239855302647285651L;
	
	private int _id;
	private String _message;
	
	public SampleMessage(int id, String message) {
		_id = id;
		_message = message;
	}
	
	public int getId() {
		return _id;
	}
	public void setId(int id) {
		_id = id;
	}
	public String getMessage() {
		return _message;
	}
	public void setMessage(String message) {
		_message = message;
	}
}

