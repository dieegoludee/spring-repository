package com.diego.curso.springboot.jpa.springbootjpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.diego.curso.springboot.jpa.springbootjpa.dto.PersonDto;
import com.diego.curso.springboot.jpa.springbootjpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

  // @Query("select p from Person p where p.id in (1, 2, 5)")
  // @Query("select p from Person p where p.id not in ?1") // lista las que no se
  // encuentren en la lista
  @Query("select p from Person p where p.id in ?1")
  List<Person> getPersonsByIds(List<Long> ids);

  @Query("select p from Person p where p.id=(select max(p.id) from Person p)")
  Optional<Person> getLastRegistration();

  @Query("select p.name, length(p.name) from Person p where length(p.name) = (select min(length(p.name)) from Person p)")
  List<Object[]> getShorterName();

  @Query("select p.name, length(p.name) from Person p where length(p.name) = (select max(length(p.name)) from Person p)")
  List<Object[]> getMaxName();

  @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
  Object getResumeAggregationFunction();

  @Query("select min(length(p.name)) from Person p")
  Integer getMinLengthName();

  @Query("select max(length(p.name)) from Person p")
  Integer getMaxLengthName();

  @Query("select p.name, length(p.name) from Person p")
  List<Object[]> getPersonNameLength();

  @Query("select count(p) from Person p")
  Long getTotalPersons();

  @Query("select min(p.id) from Person p")
  Long getMinId();

  @Query("select max(p.id) from Person p")
  Long getMaxId();

  List<Person> findAllByOrderByNameAscLastnameDesc(); // método sin anotación Query

  @Query("select p from Person p order by p.name, p.lastname desc")
  List<Person> getAllOrderedList();

  List<Person> findByIdBetweenOrderByNameAsc(Long id1, Long id2); // método sin anotación Query

  List<Person> findByNameBetweenOrderByNameDescLastnameAsc(String name1, String name2); // método sin anotación Query

  @Query("select p from Person p where p.id between ?1 and ?2 order by p.name desc")
  List<Person> findAllBetweenId(Long id1, Long id2);

  @Query("select p from Person p where p.name between ?1 and ?2 order by p.name, p.lastname asc")
  List<Person> findAllBetweenName(String name1, String name2);

  @Query("select p.id, upper(p.name), lower(p.lastname), upper(p.programmingLanguage) from Person p")
  List<Object[]> getPersonDataListCase();

  @Query("select lower(p.name || ' ' || p.lastname) from Person p")
  List<String> findAllFullNameConcatLower();

  @Query("select upper(concat(p.name, ' ', p.lastname)) from Person p")
  List<String> findAllFullNameConcatUpper();

  // @Query("select concat(p.name, ' ', p.lastname) from Person p")
  @Query("select p.name || ' ' || p.lastname from Person p") // Otra forma de concatenar
  List<String> findAllFullNameConcat();

  @Query("select count(distinct(p.programmingLanguage)) from Person p")
  Long findAllLanguagesDistinctCount();

  @Query("select p.name from Person p")
  List<String> findAllNames();

  @Query("select distinct(p.name) from Person p")
  List<String> findAllNamesDistinct();

  @Query("select distinct(p.programmingLanguage) from Person p")
  List<String> findAllLanguagesDistinct();

  @Query("select new Person(p.name, p.lastname) from Person p")
  List<Person> findAllObjectPersonPersonalized();

  @Query("select new com.diego.curso.springboot.jpa.springbootjpa.dto.PersonDto(p.name, p.lastname) from Person p")
  List<PersonDto> findAllPersonDto();

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

  @Query("select p, p.programmingLanguage from Person p")
  List<Object[]> findAllMixPerson();

  @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p")
  List<Object[]> buscarPersonDataList();

  @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id=?1")
  Optional<Object> buscarPersonDataById(Long id);

  @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1 and p.name=?2")
  List<Object[]> buscarPersonDataByValues(String programmingLanguage, String name);

}
