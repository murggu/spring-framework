package com.aitormurguzur.spring.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("jmsMessageListener")
public class JmsMessageListener implements MessageListener {

	private static Logger logger = LoggerFactory.getLogger(JmsMessageListener.class);
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	 
	public void onMessage(Message message) {
		try {
			SampleMessage sampleMessage = (SampleMessage) ((ObjectMessage) message).getObject();
			logger.info("Received message {id:{}, message:{}}", sampleMessage.getId(), sampleMessage.getMessage());
			
			saveToBD(sampleMessage);
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
	}
	
	@Transactional
    private void saveToBD(SampleMessage sampleMessage) {
            String query = "insert into JMSMESSAGES(id, message) values (?,?)";
            jdbcTemplate.update(query, sampleMessage.getId(), sampleMessage.getMessage());
    }
}
