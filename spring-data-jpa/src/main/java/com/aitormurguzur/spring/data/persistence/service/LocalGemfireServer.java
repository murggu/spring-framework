package com.aitormurguzur.spring.data.persistence.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class LocalGemfireServer {

	public static void main(String[] args) throws IOException, InterruptedException {
		@SuppressWarnings({ "unused", "resource" })
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/gemfire-cache-config.xml");

		System.out.println("Press <ENTER> to terminate the cache server");
		System.in.read();
		System.exit(0);
	}
}
