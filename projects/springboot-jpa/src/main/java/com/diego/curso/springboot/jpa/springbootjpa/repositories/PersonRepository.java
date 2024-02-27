package com.diego.curso.springboot.jpa.springbootjpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.diego.curso.springboot.jpa.springbootjpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

  @Query("select p.name from Person p where p.id=?1")
  String getNameById(Long id);

  @Query("select p.id from Person p where p.id=?1")
  Long getIdById(Long id);

  @Query("select concat(p.name, ' ', p.lastname) as fullname from Person p where p.id=?1")
  String getFullNameById(Long id);

  @Query("select p from Person p where p.id=?1")
  Optional<Person> findOne(Long id);

  @Query("select p from Person p where p.name=?1")
  Optional<Person> findOneName(String name);

  @Query("select p from Person p where p.name like %?1%") // %?1% para buscar a la derecha e izquierda del param
  Optional<Person> findOneLikeName(String name);

  Optional<Person> findByNameContaining(String name); // nombre del método sin la anotación Query con like

  List<Person> findByProgrammingLanguage(String programmingLanguage);

  @Query("select p from Person p where p.programmingLanguage=?1")
  List<Person> buscarByProgrammingLanguage(String programmingLanguage);

  List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

  // Es un array de Objects, ya que al buscar los valores name se queda con
  // Object[0] y lenguaje con Object[1]
  @Query("select p.name, p.programmingLanguage from Person p")
  List<Object[]> buscarPersonData();

  @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p")
  List<Object[]> buscarPersonDataList();

  @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id=?1")
  Optional<Object> buscarPersonDataById(Long id);

  @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1 and p.name=?2")
  List<Object[]> buscarPersonDataByValues(String programmingLanguage, String name);

}
