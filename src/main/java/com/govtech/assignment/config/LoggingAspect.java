package com.govtech.assignment.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Aspect
@Component
public class LoggingAspect {

	@Autowired
	private ObjectMapper mapper;

	@Pointcut("execution(* com.govtech.assignment.controller.*.*(..))")
	private void exceptions() {
	}

	@Pointcut("execution(* com.govtech.assignment.controller.*.*(..))")
	private void controller() {
	}

	@Pointcut("execution(* com.govtech.assignment.service.*.*(..))")
	private void businessPackages() {
	}

	@Pointcut("execution(* com.govtech.assignment.mapper.*.*(..))")
	private void mapperPackages() {
	}

	@Pointcut("execution(* com.govtech.assignment.repository.*.*(..))")
	private void repositoryPackages() {
	}

	@Pointcut("execution(public * *(..))")
	private void loggingPublicOperation() {
	}

	@Before("controller() && args(.., @RequestBody body)")
	public void logBeforeExecution(final JoinPoint joinPoint, final Object body) {
		Logger log = getLog(joinPoint);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		log.info("Request URL : {} {}", request.getMethod(), request.getRequestURI());
		log.info("Request Headers: {}", deriveRequiredHeaders(request));
		try {
			if (body != null) {
				StringJoiner requestJoiner = new StringJoiner("\n");
				requestJoiner.add(mapper.writeValueAsString(body));
				log.info("Request :  {} ", requestJoiner);
			}
		} catch (JsonProcessingException e) {
			log.info("Request :  {} ", Arrays.toString(joinPoint.getArgs()));
		}
	}

	@AfterThrowing(pointcut = "exceptions()", throwing = "exception")
	public void logsErrors(JoinPoint joinPoint, Throwable exception) {
		Logger log = getLog(joinPoint);
		log.error("An exception has been thrown at {} : {}", deriveMethodName(joinPoint), exception.getMessage());
	}

	@Around("(controller() && loggingPublicOperation()) || (businessPackages() && loggingPublicOperation()) || (mapperPackages() && loggingPublicOperation())")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		return logAroundBusinessPackages(joinPoint);
	}

	private Object logAroundBusinessPackages(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger log = getLog(joinPoint);
		String methodName = deriveMethodName(joinPoint);
		log.info("{} - Start", methodName);
		long start = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long elapsedTime = System.currentTimeMillis() - start;
		log.info("{} - End with execution time : {} ms", methodName, elapsedTime);
		return result;
	}

	@Around("(repositoryPackages() && loggingPublicOperation())")
	public Object logAroundRepository(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger log = getLog(joinPoint);
		Object result = logAroundBusinessPackages(joinPoint);
		if (result != null && result instanceof List) {
			log.info("Query results size :  {} ", ((List<?>) result).size());
		}
		return result;
	}

	private Logger getLog(JoinPoint joinPoint) {
		Logger log = null;
		try {
			log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		} catch (Exception ex) {
			log = LoggerFactory.getLogger(this.getClass());
		}
		return log;
	}

	private String deriveMethodName(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		return deriveClassName(joinPoint) + "." + methodSignature.getName();
	}

	private String deriveClassName(JoinPoint joinPoint) {
		return joinPoint.getSignature().getDeclaringType().getSimpleName();
	}

	private String deriveRequiredHeaders(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return map.toString();
	}
}