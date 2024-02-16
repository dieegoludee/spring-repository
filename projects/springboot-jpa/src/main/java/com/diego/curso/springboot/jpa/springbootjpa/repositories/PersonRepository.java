package com.diego.curso.springboot.jpa.springbootjpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.diego.curso.springboot.jpa.springbootjpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

  List<Person> findByProgrammingLanguage(String programmingLanguage);

  @Query("select p from Person p where p.programmingLanguage=?1")
  List<Person> buscarByProgrammingLanguage(String programmingLanguage);

  List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

  // Es un array de Objects, ya que al buscar los valores name se queda con
  // Object[0] y lenguaje con Object[1]
  @Query("select p.name, p.programmingLanguage from Person p")
  List<Object[]> buscarPersonData();

  @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1 and p.name=?2")
  List<Object[]> buscarPersonDataByValues(String programmingLanguage, String name);

}
