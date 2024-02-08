package com.diego.springboot.ioc.app.springbootioc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import com.diego.springboot.ioc.app.springbootioc.repositories.ProductRepository;
import com.diego.springboot.ioc.app.springbootioc.repositories.ProductRepositoryJson;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

  @Value("classpath:json/product.json")
  private Resource resource;

  @Bean("productJson")
  ProductRepository productRepositoryJson() {
    return new ProductRepositoryJson(resource);
  }
}
