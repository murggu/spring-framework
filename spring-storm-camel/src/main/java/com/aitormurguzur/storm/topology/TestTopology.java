package com.aitormurguzur.storm.topology;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aitormurguzur.storm.bolt.WordCounterBolt;
import com.aitormurguzur.storm.jms.SpringJmsProvider;
import com.aitormurguzur.storm.spout.WordGeneratorSpout;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.contrib.jms.JmsMessageProducer;
import backtype.storm.contrib.jms.JmsProvider;
import backtype.storm.contrib.jms.bolt.JmsBolt;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Tuple;

public class TestTopology {

	@SuppressWarnings("serial")
	public static void main (String[] args) {
		ApplicationContext appConfig = new ClassPathXmlApplicationContext("app-config.xml");
		JmsProvider jmsProvider = new SpringJmsProvider(appConfig, "jmsConnectionFactory", "notificationQueue");
		
		JmsBolt jmsBolt = new JmsBolt();
		jmsBolt.setJmsProvider(jmsProvider);
		jmsBolt.setJmsMessageProducer(new JmsMessageProducer() {
			public Message toMessage(Session session, Tuple tuple)
					throws JMSException {
				String jsonString = "{\"word\":\"" + tuple.getString(0)
						+ "\", \"count\":" + tuple.getInteger(1) 
						+ "}";
				return session.createTextMessage(jsonString);
			}
		});
		
		TopologyBuilder topologyBuilder = new TopologyBuilder();
		topologyBuilder.setSpout("wordGeneratorSpout", new WordGeneratorSpout());
		topologyBuilder.setBolt("wordCounterBolt", new WordCounterBolt()).shuffleGrouping("wordGeneratorSpout");
		topologyBuilder.setBolt("jmsBolt", jmsBolt).shuffleGrouping("wordCounterBolt");
		
		Config topologyConfig = new Config();
		topologyConfig.setDebug(false);
		
		LocalCluster localCluster = new LocalCluster();
		localCluster.submitTopology("testWordTopology", topologyConfig, topologyBuilder.createTopology());
	}
}
