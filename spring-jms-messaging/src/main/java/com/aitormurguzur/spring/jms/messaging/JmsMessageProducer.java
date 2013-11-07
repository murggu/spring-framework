package com.aitormurguzur.spring.jms.messaging;

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
	
	@Autowired
	private JmsTemplate jmsTopicTemplate;
	
	public void convertAndSendMessage(SampleMessage sampleMessage) {
		jmsTemplate.convertAndSend(sampleMessage);
		logger.info("Sending message {id:{}, message:{}}", sampleMessage.getId(), sampleMessage.getMessage());
	}
	
	public void convertAndSendMessage(String destination, SampleMessage sampleMessage) {
		jmsTemplate.convertAndSend(destination, sampleMessage);
		logger.info("Sending message {id:{}, message:{}}", sampleMessage.getId(), sampleMessage.getMessage());
	}
	
	public void convertAndSendTopic(String destination, SampleMessage sampleMessage) {
        jmsTopicTemplate.convertAndSend(destination, sampleMessage);
	}
}
