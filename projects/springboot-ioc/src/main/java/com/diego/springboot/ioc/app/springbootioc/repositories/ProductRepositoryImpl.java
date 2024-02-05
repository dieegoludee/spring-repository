package com.diego.springboot.ioc.app.springbootioc.repositories;

import java.util.Arrays;
import java.util.List;

import com.diego.springboot.ioc.app.springbootioc.models.Product;

public class ProductRepositoryImpl implements ProductRepository {

  private List<Product> data;

  public ProductRepositoryImpl() {
    this.data = Arrays.asList(
        new Product(1L, "Memoria Corsair 32", 300L),
        new Product(2L, "CPU Intel Core i9", 850L),
        new Product(3L, "Teclado Razer Mini 60%", 180L),
        new Product(4L, "Motherboard Gigabyte", 490L));
  }

  @Override
  public List<Product> findAll() {
    return data;
  }

  @Override
  public Product findById(Long id) {
    // retorna el objeto Product si la expresión lambda es true.
    // con el .orElse(null) devuelve el objeto si lo encuentra, sino devuelve null
    return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
  }
}
