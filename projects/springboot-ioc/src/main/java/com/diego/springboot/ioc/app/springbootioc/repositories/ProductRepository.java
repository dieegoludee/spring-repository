package com.diego.springboot.ioc.app.springbootioc.repositories;

import java.util.List;

import com.diego.springboot.ioc.app.springbootioc.models.Product;

public interface ProductRepository {

  List<Product> findAll();

  Product findById(Long id);

}
