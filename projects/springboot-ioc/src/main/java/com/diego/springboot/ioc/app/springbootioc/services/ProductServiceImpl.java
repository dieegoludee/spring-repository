package com.diego.springboot.ioc.app.springbootioc.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.springboot.ioc.app.springbootioc.models.Product;
import com.diego.springboot.ioc.app.springbootioc.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository repository;

  // Con el constructor no hace falta poner la anotación @Autowired
  // public ProductServiceImpl(ProductRepository repository) {
  // this.repository = repository;
  // }

  @Override
  public List<Product> findAll() {
    return repository.findAll().stream().map(p -> {
      Double priceTax = p.getPrice() * 1.25d;
      // Para evitar la mutabilidad del objeto, clonamos o creamos una nueva instancia
      // del objeto
      Product newProd = (Product) p.clone();
      newProd.setPrice(priceTax.longValue());
      return newProd;
    }).collect(Collectors.toList());
  }

  @Override
  public Product findById(Long id) {
    return repository.findById(id);
  }

  // Autowired mediante método Setter (I.D)
  // @Autowired
  // public void setRepository(ProductRepository repository) {
  // this.repository = repository;
  // }

}
