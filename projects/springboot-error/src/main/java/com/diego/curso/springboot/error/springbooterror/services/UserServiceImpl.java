package com.diego.curso.springboot.error.springbooterror.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.curso.springboot.error.springbooterror.models.User;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private List<User> users;

  @Override
  public Optional<User> findById(Long id) {
    return users.stream().filter(user -> user.getId().equals(id)).findFirst();
  }

  @Override
  public List<User> findAll() {
    return users;
  }

}
