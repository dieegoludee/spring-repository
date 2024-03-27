package com.diego.curso.springboot.app.springbootcrud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.curso.springboot.app.springbootcrud.entities.User;
import com.diego.curso.springboot.app.springbootcrud.services.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*") // para CORS
@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService service;

  @GetMapping
  public List<User> listAll() {
    return service.findAll();
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')") // Para autorizar método mediante Anotaciones si tiene rol ADMIN
  public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult bindingResult) {
    if (bindingResult.hasFieldErrors()) {
      return validation(bindingResult);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult bindingResult) {
    user.setAdmin(false);
    return create(user, bindingResult);
  }

  private ResponseEntity<?> validation(BindingResult bindingResult) { // método para validar campos
    Map<String, String> errors = new HashMap<>();
    bindingResult.getFieldErrors().forEach(error -> {
      errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
    });

    return ResponseEntity.badRequest().body(errors);
  }
}
