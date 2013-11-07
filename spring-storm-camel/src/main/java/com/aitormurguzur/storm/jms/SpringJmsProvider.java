package com.aitormurguzur.storm.jms;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.springframework.context.ApplicationContext;

import backtype.storm.contrib.jms.JmsProvider;

public class SpringJmsProvider implements JmsProvider {

	private static final long serialVersionUID = 71376559394443254L;
	
	private ConnectionFactory connectionFactory;
	private Destination destination;
	
	public SpringJmsProvider(ApplicationContext context, String connectionFactoryBean, String destinationBean) {
		this.connectionFactory = (ConnectionFactory) context.getBean(connectionFactoryBean);
		this.destination = (Destination) context.getBean(destinationBean);
	}
	
	public ConnectionFactory connectionFactory() throws Exception {
		return this.connectionFactory;
	}

	public Destination destination() throws Exception {
		return this.destination;
	}
}
