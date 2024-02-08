package com.diego.springboot.ioc.app.springbootioc.repositories;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.diego.springboot.ioc.app.springbootioc.models.Product;

@Repository
public class ProductRepositoryFoo implements ProductRepository {

  @Override
  public List<Product> findAll() {
    return Collections.singletonList(new Product(1L, "Monitor Asus 27", 600L));
  }

  @Override
  public Product findById(Long id) {
    return new Product(id, "Monitor Asus 27", 600L);
  }

}
