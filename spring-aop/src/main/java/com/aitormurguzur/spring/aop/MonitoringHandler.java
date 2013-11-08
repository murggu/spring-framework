package com.aitormurguzur.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("monitoringHandler")
@Aspect
public class MonitoringHandler {

	private static Logger logger = LoggerFactory.getLogger(MonitoringHandler.class);
	
	@Around("execution(* com.aitormurguzur.spring.aop.SampleLogic.*(..))")
	public String monitorExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long execStartTime = System.currentTimeMillis();
		String result = (String) joinPoint.proceed();
		long execTime = System.currentTimeMillis() - execStartTime;
		logger.debug("Execution time {method:{}, totalTime:{}}", joinPoint.getSignature().getName(), execTime);
		return result;
	}
}
