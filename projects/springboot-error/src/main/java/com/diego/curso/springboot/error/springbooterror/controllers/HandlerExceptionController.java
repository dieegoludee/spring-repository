package com.diego.curso.springboot.error.springbooterror.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.diego.curso.springboot.error.springbooterror.models.Error;

@RestControllerAdvice
public class HandlerExceptionController {

  // Con llaves para añadir más de una excepción
  @ExceptionHandler({ ArithmeticException.class })
  public ResponseEntity<Error> divisionByZero(Exception exception) {
    Error error = new Error();
    error.setDate(new Date());
    error.setError("Error división por 0");
    error.setMessage(exception.getMessage());
    error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

    // return ResponseEntity.internalServerError().body(error);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
  }

  @ExceptionHandler(NumberFormatException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Map<String, Object> numberFormatException(Exception exception) {
    Map<String, Object> error = new HashMap<>();
    error.put("date", new Date());
    error.put("error", "Número inválido o incorrecto, no tiene el formato deseado");
    error.put("message", exception.getMessage());
    error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

    return error;
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<Error> notFoundException(NoHandlerFoundException exception) {
    Error error = new Error();
    error.setDate(new Date());
    error.setError("API REST no encontrado");
    error.setMessage(exception.getMessage());
    error.setStatus(HttpStatus.NOT_FOUND.value());

    return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
  }
}
