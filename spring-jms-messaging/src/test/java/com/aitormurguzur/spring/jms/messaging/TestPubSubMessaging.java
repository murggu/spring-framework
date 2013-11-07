package com.aitormurguzur.spring.jms.messaging;

import static org.junit.Assert.assertEquals;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aitormurguzur.spring.jms.messaging.JmsTopicListener;
import com.aitormurguzur.spring.jms.messaging.SampleMessage;

/**
 * Tests publisher/subscribe pattern
 * @author amurguzur
 *
 */
@ContextConfiguration(locations = { "/spring/jms-config.xml",  "/spring/app-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPubSubMessaging extends TestJmsMessaging {

	@Before
	public void clearRegistry() {
	        messageRegistry.clear();
	}
	
	/**
	 * Tests pub/sub using 4 subscribers for the same topic (i.e. one.topic)
	 * @throws InterruptedException
	 * @throws JMSException
	 */
	@Test
	public void testAsynchronizedMessaging() throws InterruptedException, JMSException {
		SampleMessage sampleMessage = new SampleMessage(2, "this is a topic message to deliver correctly");
		
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageConsumer consumer = session.createConsumer(topic);
		MessageListener listener = new JmsTopicListener(messageRegistry);
		consumer.setMessageListener(listener);
		
		jmsMessageProducer.convertAndSendTopic(TOPIC_ONE, sampleMessage);
		
		Thread.sleep(3000);
		connection.close();
		
		assertEquals(4, messageRegistry.getReceivedMessages().size());
		assertEquals(sampleMessage.getMessage(), messageRegistry.getReceivedMessages().get(0).getMessage());
		assertEquals(sampleMessage.getMessage(), messageRegistry.getReceivedMessages().get(1).getMessage());
		assertEquals(sampleMessage.getMessage(), messageRegistry.getReceivedMessages().get(2).getMessage());
		assertEquals(sampleMessage.getMessage(), messageRegistry.getReceivedMessages().get(3).getMessage());
	}
}
