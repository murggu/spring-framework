package com.aitormurguzur.spring.jms.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.jms.support.JmsUtils;

public class JmsTopicListener implements MessageListener {

	private MessageRegistry _registry;
	
	public JmsTopicListener(MessageRegistry registry) {
		_registry = registry;
	}
	
	public void onMessage(Message message) {
		try {
			SampleMessage receivedMessage = (SampleMessage) ((ObjectMessage) message).getObject();
			_registry.registerMessage(receivedMessage);
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
	}

}
