package com.aitormurguzur.spring.jms.messaging;

import java.util.Enumeration;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.Topic;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aitormurguzur.spring.jms.messaging.JmsMessageProducer;
import com.aitormurguzur.spring.jms.messaging.JmsSyncListener;
import com.aitormurguzur.spring.jms.messaging.MessageRegistry;

@ContextConfiguration(locations = { "/spring/app-config.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestJmsMessaging {

	protected static final String QUEUE_SYNC = "sync.queue";
	protected static final String QUEUE_ASYNC = "async.queue";
	protected static final String TOPIC_ONE = "one.topic";

	@Autowired
	protected JmsTemplate jmsTemplate;
	
	@Autowired
	protected JmsMessageProducer jmsMessageProducer;
	
	@Autowired
	protected JmsSyncListener jmsSyncListener;
	
	@Autowired
	protected MessageRegistry messageRegistry;
	
	@Autowired
	protected ConnectionFactory connectionFactory;
    
	@Autowired
	protected Topic topic;
	
	protected int getMessagesInQueue(String queueName) {
		return jmsTemplate.browse(queueName, new BrowserCallback<Integer>() {
			public Integer doInJms(Session session, QueueBrowser browser) throws JMSException {
				Enumeration<?> messages = browser.getEnumeration();
				int total = 0;
				while (messages.hasMoreElements()) {
					messages.nextElement();
					total++;
				}

				return total;
			}
		});
	}
}
