package com.aitormurguzur.spring.jms.tx;

import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.QueueBrowser;
import javax.jms.Session;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aitormurguzur.spring.jms.tx.JmsMessageProducer;

@ContextConfiguration(locations = { "/spring/app-config.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestJmsMessaging {

	protected static final String QUEUE_INCOMING = "incoming.queue";
    protected static final String QUEUE_DLQ = "ActiveMQ.DLQ";
    
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Autowired
	protected JmsTemplate jmsTemplate;

	@Autowired
	protected JmsMessageProducer jmsMessageProducer;

	@Before
	public void dropJmsMessages() {
		jdbcTemplate.update("delete from JMSMESSAGES");
	}
	
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

	protected int getSavedMessages() {
		return jdbcTemplate.queryForObject("select count(*) from JMSMESSAGES", Integer.class);
	}
}
