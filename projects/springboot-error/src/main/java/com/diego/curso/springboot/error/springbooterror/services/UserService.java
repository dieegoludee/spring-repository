package com.diego.curso.springboot.error.springbooterror.services;

import java.util.List;
import java.util.Optional;

import com.diego.curso.springboot.error.springbooterror.models.User;

public interface UserService {

  Optional<User> findById(Long id);

  List<User> findAll();

}
