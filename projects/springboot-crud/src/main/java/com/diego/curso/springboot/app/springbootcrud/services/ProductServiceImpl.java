package com.diego.curso.springboot.app.springbootcrud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diego.curso.springboot.app.springbootcrud.entities.Product;
import com.diego.curso.springboot.app.springbootcrud.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository repository;

  @Transactional(readOnly = true)
  @Override
  public List<Product> findAll() {
    return (List<Product>) repository.findAll();
  }

  @SuppressWarnings("null")
  @Transactional(readOnly = true)
  @Override
  public Optional<Product> findById(Long id) {
    return repository.findById(id);
  }

  @SuppressWarnings("null")
  @Transactional
  @Override
  public Product save(Product product) {
    return repository.save(product);
  }

  @SuppressWarnings("null")
  @Transactional
  @Override
  public Optional<Product> delete(Product product) {
    Optional<Product> productOptional = repository.findById(product.getId());
    productOptional.ifPresent(productDb -> {
      repository.delete(productDb);
    });

    return productOptional;
  }

}
