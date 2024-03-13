package com.diego.curso.springboot.app.springbootcrud.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.curso.springboot.app.springbootcrud.entities.Product;
import com.diego.curso.springboot.app.springbootcrud.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService service;

  @GetMapping
  public List<Product> list() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> view(@PathVariable Long id) {
    Optional<Product> productOptional = service.findById(id);
    if (productOptional.isPresent()) {
      return ResponseEntity.ok(productOptional.orElseThrow());
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Product product) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
    Optional<Product> productOptional = service.update(id, product);
    if (productOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Product> productOptional = service.delete(id);
    if (productOptional.isPresent()) {
      return ResponseEntity.ok(productOptional.orElseThrow());
    }

    return ResponseEntity.notFound().build();
  }

}
