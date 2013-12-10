spring-framework
================

Samples using Spring Framework

- **spring-aop**: AOP is often used to modularize cross-cutting concerns in aspects (e.g. logging, exception handling, monitoring, transaction management or security). In this example, the SampleLogic class which contains basic business logic, while three handlers (LoggingHandler, ExceptionHandler and MonitoringHandler) include aspects (using AspectJ) to inject cross-cutting concerns.
- **spring-data-jpa**: An example following the spring-data tutorial (https://github.com/spring-guides/tut-data) to store and retrieve data from multiple data stores. In this particular case, three data models have been integrated: relational  using PostgreSQL, document-oriented using MongoDB and a caching data solution with GemFire.
- **spring-data-redis**: A wrapper for the spring-data-redis.
- **spring-jms-messaging**: An example for processing JMS messages in different ways: using queues for point-to-point sync and async reception and topics for pub/sub communication. You can run it as an standalone application.
- **spring-jms-tx**: An example for processing JMS messages using local transactions, ActiveMQ broker and HSQLBD. You can run it as an standalone application.
- **spring-storm-camel**: An standalone application for demostrating real-time stream processing using ActiveMQ and Camel. The WordGeneratorSpout generates a random people name  every 200 milliseconds which is later counted by the WordCounterBolt. The bolt counts the frequency of the each people name passed from the spout, which finally is written to ActiveMQ (using the JmsBolt) and received by Camel.
