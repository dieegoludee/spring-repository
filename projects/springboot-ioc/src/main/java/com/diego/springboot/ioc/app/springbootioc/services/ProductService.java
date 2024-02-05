package com.diego.springboot.ioc.app.springbootioc.services;

import java.util.List;

import com.diego.springboot.ioc.app.springbootioc.models.Product;

public interface ProductService {

  List<Product> findAll();

  Product findById(Long id);
}
