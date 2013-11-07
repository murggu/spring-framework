package com.aitormurguzur.spring.jms.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component("jmsSyncListener")
public class JmsSyncListener {

	 @Autowired
     private JmsTemplate jmsTemplate;
     
     public SampleMessage receive() {
             return (SampleMessage) jmsTemplate.receiveAndConvert("sync.queue");
     }
}
