package com.aitormurguzur.spring.jms.tx;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aitormurguzur.spring.jms.tx.SampleMessage;

/**
 * Tests JMS using local transactions
 * @author amurguzur
 *
 */
@ContextConfiguration(locations = { "/spring/jms-config.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestTransactedMessaging extends TestJmsMessaging {

	private static Logger logger = LoggerFactory.getLogger(TestTransactedMessaging.class);

	/**
	 * Tests the correct sending, receiving and saving of a message
	 * @throws InterruptedException
	 */
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
	
	/**
	 * Tests an error after receiving a message. If it isn't saved into DB, redirect to ActiveMQ.DLQ (Dead Letter Queue)
	 * @throws InterruptedException
	 */
	@Test
    public void testFailedAfterReceiveMessage() throws InterruptedException {
        SampleMessage sampleMessage = new SampleMessage(1, "this is a message to fail after receiving");
        jmsMessageProducer.convertAndSendMessage(QUEUE_INCOMING, sampleMessage);
        
        Thread.sleep(6000);
        printResults();
        
        assertEquals(0, getSavedMessages());
        assertEquals(0, getMessagesInQueue(QUEUE_INCOMING));
        assertEquals(1, getMessagesInQueue(QUEUE_DLQ));
        //Empty the dead letter queue
        jmsTemplate.receive(QUEUE_DLQ);
    }
	
	private void printResults() {
		logger.info("Total messages in \"incoming\" queue: {}", getMessagesInQueue(QUEUE_INCOMING));
		logger.info("Total items in \".DLQ\" queue: "+getMessagesInQueue(QUEUE_DLQ));
		logger.info("Total messages in DB: {}", getSavedMessages());
	}
}
