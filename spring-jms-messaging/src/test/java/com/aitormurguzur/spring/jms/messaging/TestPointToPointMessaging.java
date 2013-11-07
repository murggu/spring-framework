package com.aitormurguzur.spring.jms.messaging;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aitormurguzur.spring.jms.messaging.SampleMessage;

@ContextConfiguration(locations = { "/spring/jms-config.xml",  "/spring/app-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPointToPointMessaging extends TestJmsMessaging {

	private static Logger logger = LoggerFactory.getLogger(TestPointToPointMessaging.class);
	
	@Test
	public void testSynchronizedMessaging() throws InterruptedException {
		SampleMessage sampleMessage = new SampleMessage(0, "this is a message to deliver synchronously");
		jmsMessageProducer.convertAndSendMessage(sampleMessage);
		
		Thread.sleep(2000);
		
		SampleMessage receivedMessage = jmsSyncListener.receive();
		printResults(QUEUE_SYNC);
		assertEquals(sampleMessage.getMessage(), receivedMessage.getMessage());
		assertEquals(0, getMessagesInQueue(QUEUE_SYNC));
	}
	
	@Test
	public void testAsynchronizedMessaging() throws InterruptedException {
		SampleMessage sampleMessage = new SampleMessage(1, "this is a message to deliver asynchronously");
		jmsMessageProducer.convertAndSendMessage(QUEUE_ASYNC, sampleMessage);
		
		Thread.sleep(2000);
		printResults(QUEUE_ASYNC);
		
		assertEquals(1, messageRegistry.getReceivedMessages().size());
		assertEquals(sampleMessage.getMessage(), messageRegistry.getReceivedMessages().get(0).getMessage());
		assertEquals(0, getMessagesInQueue(QUEUE_ASYNC));
	}

	private void printResults(String queue) {
		logger.info("Total items in \""+ queue +"\" queue: " + getMessagesInQueue(queue));
	}
}
