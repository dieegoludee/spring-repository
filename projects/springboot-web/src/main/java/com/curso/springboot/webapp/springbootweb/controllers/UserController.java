package com.curso.springboot.webapp.springbootweb.controllers;

import java.util.Arrays;
import java.util.List;

//import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.curso.springboot.webapp.springbootweb.models.User;

@Controller
public class UserController {

  // Método para pasar datos mediante el Model
  @GetMapping("/details")
  public String detailsModel(Model model) {
    User user = new User("Diego", "Ludeña");
    user.setEmail("email@email.com");

    model.addAttribute("title", "Hola mundo Spring Boot");
    model.addAttribute("user", user);

    return "details";
  }

  @GetMapping("/list")
  public String list(ModelMap model) {
    model.addAttribute("title", "Listado de usuarios");
    // model.addAttribute("users", users);

    return "list";
  }

  @ModelAttribute("users") // Se guarda en el users de la vista
  public List<User> usersModel() {
    return Arrays.asList(
        new User("Pepa", "González"),
        new User("Lalo", "Pérez", "lalo@correo.com"),
        new User("Juanita", "Doe", "juana@correo.com"),
        new User("Andrés", "Roe"));
  }

  // Método para pasar datos mediante el Map
  // @GetMapping("/detailsMap")
  // public String detailsMap(Map<String, Object> model) {
  // model.put("title", "Hola mundo Spring Boot");
  // model.put("name", "diego");
  // model.put("lastname", "ludeña");

  // return "details";
  // }
}
