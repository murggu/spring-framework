package com.aitormurguzur.spring.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component("jmsMessageProducer")
public class JmsMessageProducer {

	public Logger logger = LoggerFactory.getLogger(JmsMessageProducer.class);
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void convertAndSendMessage(String destination, SampleMessage sampleMessage) {
		jmsTemplate.convertAndSend(destination, sampleMessage);
		logger.info("Sending message {id:{}, message:{}}", sampleMessage.getId(), sampleMessage.getMessage());
	}
}
