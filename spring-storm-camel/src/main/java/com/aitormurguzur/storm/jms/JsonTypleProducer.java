package com.aitormurguzur.storm.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import backtype.storm.contrib.jms.JmsTupleProducer;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class JsonTypleProducer implements JmsTupleProducer {

	private static final long serialVersionUID = -4506194841728521447L;

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields ("jsonString"));
	}

	public Values toTuple(Message message) throws JMSException {
		if (message instanceof TextMessage) {
			String jsonString = ((TextMessage) message).getText();
			return new Values(jsonString);
		} else return null;
	}
}
