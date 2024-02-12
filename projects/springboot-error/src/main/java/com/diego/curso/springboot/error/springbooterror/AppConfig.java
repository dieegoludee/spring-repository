package com.diego.curso.springboot.error.springbooterror;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.diego.curso.springboot.error.springbooterror.models.User;

@Configuration
public class AppConfig {

  @Bean
  List<User> users() {
    User user1 = new User(1L, "Pepe", "Gonzalez");
    User user2 = new User(2L, "Andres", "Mena");
    User user3 = new User(3L, "Maria", "Perez");
    User user4 = new User(4L, "Josefa", "Ramirez");
    User user5 = new User(5L, "Diego", "Martin");

    return Arrays.asList(user1, user2, user3, user4, user5);
  }

}
