package com.diego.curso.springboot.app.aop.springbootaop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class GreetingAspect {

  private static final Logger logger = LoggerFactory.getLogger(GreetingAspect.class);

  // (PointCut) Se ejecuta para devolver el String del método sayHello de la
  // interfaz,
  // con (..) se dice todos los args (regexp)
  @Before("execution(* com.diego.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")
  public void loggerBefore(JoinPoint joinPoint) {
    // Nombre del método
    String method = joinPoint.getSignature().getName();
    // Nombre de los argumentos
    String args = Arrays.toString(joinPoint.getArgs());

    logger.info("Antes: " + method + " con los argumentos" + args);
  }

  @After("execution(* com.diego.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")
  public void loggerAfter(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());
    logger.info("Despues: " + method + " con los argumentos" + args);
  }

  @AfterReturning("execution(* com.diego.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")
  public void loggerAfterReturning(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());
    logger.info("Despues de retornar: " + method + " con los argumentos" + args);
  }

  @AfterThrowing("execution(* com.diego.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")
  public void loggerAfterThrowing(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());
    logger.info("Despues de lanzar la excepcion: " + method + " con los argumentos" + args);
  }

}
