spring-framework
================

Samples using Spring Framework

- **spring-jms-tx**: An example for processing JMS messages using local transactions, ActiveMQ broker and HSQLBD. You can run it as an standalone application.
- **spring-jms-messaging**: An example for processing JMS messages in different ways: using queues for point-to-point sync and async reception and topics for pub/sub communication. You can run it as an standalone application.
- **spring-storm-camel**: An standalone application for demostrating real-time stream processing using ActiveMQ and Camel. The WordGeneratorSpout generates a random people name  every 200 milliseconds which is later counted by the WordCounterBolt. The bolt counts the frequency of the each people name passed from the spout, which finally is written to ActiveMQ (using the JmsBolt) and received by Camel.
