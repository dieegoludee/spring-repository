package com.diego.curso.springboot.app.aop.springbootaop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@Aspect
public class GreetingFooAspect {

  private static final Logger logger = LoggerFactory.getLogger(GreetingFooAspect.class);

  @Before("GreetingServicePointcuts.greetingFooLoggerPointCut()")
  public void loggerBefore(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());
    logger.info("Antes Foo: " + method + " invocado con los argumentos" + args);
  }

  @After("GreetingServicePointcuts.greetingFooLoggerPointCut()")
  public void loggerAfter(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());
    logger.info("Despues Foo: " + method + " invocado con los argumentos" + args);
  }
}
