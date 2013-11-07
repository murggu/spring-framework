package com.aitormurguzur.storm.camel;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBean {

	private static Logger logger = LoggerFactory.getLogger(TestBean.class);
	
	public void printPayload(Exchange payload) {
		logger.info("Received messaged in Camel " + payload.getIn().getBody().toString());
	}
}
