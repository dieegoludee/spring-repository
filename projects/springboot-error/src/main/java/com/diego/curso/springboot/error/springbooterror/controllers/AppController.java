package com.diego.curso.springboot.error.springbooterror.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.curso.springboot.error.springbooterror.exceptions.UserNotFoundException;
import com.diego.curso.springboot.error.springbooterror.models.User;
import com.diego.curso.springboot.error.springbooterror.services.UserService;

@RestController
@RequestMapping("/app")
public class AppController {

  @Autowired
  private UserService service;

  @GetMapping
  public String index() {
    // int value = 100 / 0;
    int value = Integer.parseInt("10x");
    System.out.println(value);
    return "ok 200";
  }

  @GetMapping("/show/{id}")
  public User show(@PathVariable(name = "id") Long id) {
    User user = service.findById(id).orElseThrow(
        () -> new UserNotFoundException("Error: El Usuario no existe!"));

    return user;
  }

  // SE PUEDE HACER DE LAS 2 FORMAS

  // @GetMapping("/show/{id}")
  // public ResponseEntity<?> show(@PathVariable(name = "id") Long id) {
  // // User user = service.findById(id).orElseThrow(
  // // () -> new UserNotFoundException("Error: El Usuario no existe!"));

  // Optional<User> optionalUser = service.findById(id);
  // if (optionalUser.isEmpty()) {
  // return ResponseEntity.notFound().build();
  // }
  // return ResponseEntity.ok(optionalUser.get());
  // }
}
