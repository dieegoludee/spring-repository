package com.diego.curso.springboot.app.springbootcrud.validation;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequiredValidation implements ConstraintValidator<IsRequired, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    return StringUtils.hasText(value); // hacen lo mismo todas

    // return (value != null && !value.isEmpty() && !value.isBlank());

    // if (value != null && !value.isEmpty() && !value.isBlank())
    // return true;
    // return false;

  }

}
