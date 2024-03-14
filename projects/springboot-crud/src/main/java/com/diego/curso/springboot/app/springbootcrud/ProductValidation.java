package com.diego.curso.springboot.app.springbootcrud;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.diego.curso.springboot.app.springbootcrud.entities.Product;

@Component
public class ProductValidation implements Validator {

  @SuppressWarnings("null")
  @Override
  public boolean supports(Class<?> clazz) {
    return Product.class.isAssignableFrom(clazz);
  }

  @SuppressWarnings("null")
  @Override
  public void validate(Object target, Errors errors) {
    Product product = (Product) target;
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "es requerido!");
    // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
    // "NotBlank.product.description");

    if (product.getDescription() == null || product.getDescription().isBlank())
      errors.rejectValue("description", null, "es requerido, por favor");

    if (product.getPrice() == null)
      errors.rejectValue("price", null, "no puede ser nulo, ok!");
    else if (product.getPrice() < 500)
      errors.rejectValue("price", null, "debe de ser mayor o igual a 500!");

  }

}
