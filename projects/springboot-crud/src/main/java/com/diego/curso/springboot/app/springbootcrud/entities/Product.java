package com.diego.curso.springboot.app.springbootcrud.entities;

import com.diego.curso.springboot.app.springbootcrud.validation.IsRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(min = 3, max = 20) // size min y max del name
  @IsRequired(message = "{IsRequired.product.name}") // corresponde al messages.properties para mesajes de error
                                                     // personalizados
  private String name;

  @NotNull(message = "{NotNull.product.price}")
  @Min(message = "{Min.product.price}", value = 500) // min price
  private Integer price;

  @IsRequired // anotación personalizada que valida si el campo es nulo, vacío o con solo
              // espacios en blanco
  private String description;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
