package com.aitormurguzur.spring.jms;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "/spring/jms-config.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestTransactedMessaging extends TestJmsMessaging {

	private static Logger logger = LoggerFactory.getLogger(TestTransactedMessaging.class);

	@Test
	public void testCorrectMessage() throws InterruptedException {
		SampleMessage sampleMessage = new SampleMessage(0, "this is a message to deliver correctly");
		jmsMessageProducer.convertAndSendMessage(QUEUE_INCOMING, sampleMessage);

		Thread.sleep(6000);
		printResults();

		assertEquals(1, getSavedMessages());
		assertEquals(0, getMessagesInQueue(QUEUE_INCOMING));
		assertEquals(0, getMessagesInQueue(QUEUE_DLQ));
	}
	
	private void printResults() {
		logger.info("Total messages in \"incoming\" queue: {}", getMessagesInQueue(QUEUE_INCOMING));
		logger.info("Total messages in DB: {}", getSavedMessages());
	}
}
