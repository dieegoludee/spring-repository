package com.diego.curso.springboot.error.springbooterror.services;

import java.util.List;

import com.diego.curso.springboot.error.springbooterror.models.User;

public interface UserService {

  User findById(Long id);

  List<User> findAll();

}
