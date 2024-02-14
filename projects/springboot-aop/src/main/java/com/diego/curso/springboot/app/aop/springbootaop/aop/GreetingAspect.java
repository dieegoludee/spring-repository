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
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
@Aspect
public class GreetingAspect {

  private static final Logger logger = LoggerFactory.getLogger(GreetingAspect.class);

  @Pointcut("execution(* com.diego.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")
  private void greetingLoggerPointCut() {
  }

  // (PointCut) Se ejecuta para devolver el String del método sayHello de la
  // interfaz,
  // con (..) se dice todos los args (regexp)
  @Before("greetingLoggerPointCut()")
  public void loggerBefore(JoinPoint joinPoint) {
    // Nombre del método
    String method = joinPoint.getSignature().getName();
    // Nombre de los argumentos
    String args = Arrays.toString(joinPoint.getArgs());

    logger.info("Antes: " + method + " con los argumentos" + args);
  }

  @After("greetingLoggerPointCut()")
  public void loggerAfter(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());
    logger.info("Despues: " + method + " con los argumentos" + args);
  }

  @AfterReturning("greetingLoggerPointCut()")
  public void loggerAfterReturning(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());
    logger.info("Despues de retornar: " + method + " con los argumentos" + args);
  }

  @AfterThrowing("greetingLoggerPointCut()")
  public void loggerAfterThrowing(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());
    logger.info("Despues de lanzar la excepcion: " + method + " con los argumentos" + args);
  }

  @Around("greetingLoggerPointCut()")
  public Object loggerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    String method = proceedingJoinPoint.getSignature().getName();
    String args = Arrays.toString(proceedingJoinPoint.getArgs());

    Object result = null;
    try {
      logger.info("El metodo " + method + "() con los args" + args);
      result = proceedingJoinPoint.proceed();
      logger.info("El metodo " + method + "() retorna el resultado: " + result);
      return result;
    } catch (Throwable e) {
      logger.error("Error en la llamada del metodo: " + method + "()");
      throw e;
    }

  }
}
