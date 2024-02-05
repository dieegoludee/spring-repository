package com.diego.springboot.ioc.app.springbootioc.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.springboot.ioc.app.springbootioc.models.Product;
import com.diego.springboot.ioc.app.springbootioc.services.ProductService;

@RestController
@RequestMapping("/api")
public class SomeController {

  private ProductService service = new ProductService();

  @GetMapping()
  public List<Product> list() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Product show(@PathVariable Long id) {
    return service.findById(id);
  }
}
