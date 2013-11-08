package com.aitormurguzur.spring.aop;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests LoggingHandler, ExceptionHandler and MonitoringHandler using aspects
 * @author amurguzur
 *
 */
@ContextConfiguration(locations = { "/spring/app-config.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestHandlers {
	
	@Autowired
	SampleLogic sampleLogic;
	
	@Test
	public void testLoggingAspect(){
		/**
		 * Call first method. Exception is thrown
		 */
		sampleLogic.firstMethod();
		assertEquals(1, ExceptionRegistry.getReceivedExceptionsNumber());
		
		/**
		 * Call second method. Runs correctly
		 */
		String result = sampleLogic.secondMethod();
		assertEquals("second method result", result);
		
		/**
		 * Call third method. Exception is thrown
		 */
		sampleLogic.thirdMethod();
		assertEquals(2, ExceptionRegistry.getReceivedExceptionsNumber());
	}
}
