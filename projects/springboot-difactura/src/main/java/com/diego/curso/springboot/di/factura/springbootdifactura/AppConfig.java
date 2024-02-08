package com.diego.curso.springboot.di.factura.springbootdifactura;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.diego.curso.springboot.di.factura.springbootdifactura.models.Item;
import com.diego.curso.springboot.di.factura.springbootdifactura.models.Product;

@Configuration
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
public class AppConfig {

  @Bean
  List<Item> itemsInvoice() {
    Product p1 = new Product("Cámara Sony", 800);
    Product p2 = new Product("Bicicleta Bianchi 26X", 1200);
    // List<Item> items = Arrays.asList(new Item(p1, 2), new Item(p2, 3));

    return Arrays.asList(new Item(p1, 2), new Item(p2, 3));
  }

}
