package com.aitormurguzur.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("exceptionHandler")
@Aspect
public class ExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
	
	@Around("execution(* com.aitormurguzur.spring.aop.SampleLogic.*(..))")
	public String handleException(ProceedingJoinPoint joinPoint) throws Throwable {
		String result = "";
		try {
			result = (String) joinPoint.proceed();
		} catch(RuntimeException e) {
			logger.error("error in {}", joinPoint.getSignature().getName());
			ExceptionRegistry.registerException(joinPoint.getSignature().getName(), e);
		}
		return result;
	}
}
