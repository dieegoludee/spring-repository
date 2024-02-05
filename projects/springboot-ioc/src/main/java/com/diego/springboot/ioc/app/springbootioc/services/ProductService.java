package com.diego.springboot.ioc.app.springbootioc.services;

import java.util.List;
import java.util.stream.Collectors;

import com.diego.springboot.ioc.app.springbootioc.models.Product;
import com.diego.springboot.ioc.app.springbootioc.repositories.ProductRepository;

public class ProductService {

  private ProductRepository repository = new ProductRepository();

  public List<Product> findAll() {
    return repository.findAll().stream().map(p -> {
      Double priceImp = p.getPrice() * 1.25d;
      p.setPrice(priceImp.longValue());
      return p;
    }).collect(Collectors.toList());
  }

  public Product findById(Long id) {
    return repository.findById(id);
  }

}
