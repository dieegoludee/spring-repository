package com.diego.curso.springboot.app.interceptor.springbootinterceptor.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/app")
public class InterceptorController {

  @GetMapping("/foo")
  public Map<String, String> listInterceptorFoo() {
    return Collections.singletonMap("message", "handler listInterceptorFoo del controlador");
  }

  @GetMapping("/bar")
  public Map<String, String> listInterceptorBar() {
    return Collections.singletonMap("message", "handler listInterceptorBar del controlador");
  }

  @GetMapping("/baz")
  public Map<String, String> listInterceptorBaz() {
    return Collections.singletonMap("message", "handler listInterceptorBaz del controlador");
  }

}
