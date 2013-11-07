package com.aitormurguzur.spring.jms.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Component;

@Component("jmsAsyncListener")
public class JmsAsyncListener implements MessageListener {

	@Autowired
	private MessageRegistry messageRegistry;

	public void onMessage(Message message) {
		try {
			SampleMessage receivedMessage = (SampleMessage) ((ObjectMessage) message).getObject();
			messageRegistry.registerMessage(receivedMessage);
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
	}
}
