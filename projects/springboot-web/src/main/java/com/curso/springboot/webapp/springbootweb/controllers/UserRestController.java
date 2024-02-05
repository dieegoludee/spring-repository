package com.curso.springboot.webapp.springbootweb.controllers;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.springboot.webapp.springbootweb.models.User;
import com.curso.springboot.webapp.springbootweb.models.dto.UserDto;

@RestController
@RequestMapping("/api")
public class UserRestController {

  @GetMapping("/details")
  public UserDto details() {
    UserDto userDto = new UserDto();
    User user = new User("Diego", "Ludeña");
    userDto.setUser(user);
    userDto.setTitle("DTO");

    return userDto;
  }

  @GetMapping("/list")
  public List<User> list() {
    User diego = new User("Diego", "Ludeña");
    User pepe = new User("Pepe", "Martín");
    User mario = new User("Mario", "García");

    // ES LO MISMO QUE ABAJO, QUEDA MÁS LIMPIO
    List<User> users = Arrays.asList(diego, pepe, mario);

    // List<User> users = new ArrayList<>();
    // users.add(diego);
    // users.add(pepe);
    // users.add(mario);

    return users;
  }

  @GetMapping("/details-map")
  public Map<String, Object> detailsMap() {
    User user = new User("Diego", "Ludeña");
    Map<String, Object> body = new HashMap<>();

    body.put("title", "Hola mundo Spring Boot");
    body.put("user", user);

    return body;
  }
}
