package com.aitormurguzur.spring.jms.messaging;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("messageRegistry")
public class MessageRegistry {

	private static Logger logger = LoggerFactory.getLogger(MessageRegistry.class);
	
	private List<SampleMessage> receivedMessages = new ArrayList<SampleMessage>();
	
	public void registerMessage(SampleMessage receivedMessage) {
		logger.info("Received message {id:{}, message:{}}", 
				receivedMessage.getId(), receivedMessage.getMessage());
		receivedMessages.add(receivedMessage);
	}

	public List<SampleMessage> getReceivedMessages() {
		return receivedMessages;
	}
	
	public void clear() {
		receivedMessages.clear();
	}
}
