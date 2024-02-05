package com.diego.springboot.ioc.app.springbootioc.repositories;

import java.util.Arrays;
import java.util.List;

import com.diego.springboot.ioc.app.springbootioc.models.Product;

public class ProductRepository {

  private List<Product> data;

  public ProductRepository() {
    this.data = Arrays.asList(
        new Product(1L, "Memoria Corsair 32", 300L),
        new Product(2L, "CPU Intel Core i9", 850L),
        new Product(3L, "Teclado Razer Mini 60%", 180L),
        new Product(4L, "Motherboard Gigabyte", 490L));
  }

  public List<Product> findAll() {
    return data;
  }
}
