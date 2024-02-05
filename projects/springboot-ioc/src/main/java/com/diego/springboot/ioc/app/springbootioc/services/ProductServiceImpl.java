package com.diego.springboot.ioc.app.springbootioc.services;

import java.util.List;
import java.util.stream.Collectors;

import com.diego.springboot.ioc.app.springbootioc.models.Product;
import com.diego.springboot.ioc.app.springbootioc.repositories.ProductRepositoryImpl;

public class ProductServiceImpl implements ProductService {

  private ProductRepositoryImpl repository = new ProductRepositoryImpl();

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

}
