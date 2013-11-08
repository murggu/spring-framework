package com.aitormurguzur.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * AspectJ annotation types:
 * @Before – runs before the method execution
 * @After – runs after the method returned a result
 * @AfterReturning – runs after the method returned a result, intercept the returned result as well
 * @AfterThrowing – runs after the method throws an exception
 * @Around – runs around the method execution
 * 
 * @author amurguzur
 *
 */
@Component("loggingHandler")
@Aspect
public class LoggingHandler {

	private static Logger logger = LoggerFactory.getLogger(LoggingHandler.class);
	
	@Before("execution(* com.aitormurguzur.spring.aop.SampleLogic.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		logger.debug("Logging before method execution {}", joinPoint.getSignature().getName());
	}
	
	@After("execution(* com.aitormurguzur.spring.aop.SampleLogic.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		logger.debug("Logging after method execution {}", joinPoint.getSignature().getName());
	}
	
	@AfterReturning(pointcut = "execution(* com.aitormurguzur.spring.aop.SampleLogic.*(..))",
			returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		logger.debug("Logging after returning {method:{}, returnValue:{}}", joinPoint.getSignature().getName(), result);
	}
}
