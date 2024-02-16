package com.diego.curso.springboot.jpa.springbootjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import com.diego.curso.springboot.jpa.springbootjpa.entities.Person;
import com.diego.curso.springboot.jpa.springbootjpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// List<Person> persons = (List<Person>) repository.findAll();

		// List<Person> persons = (List<Person>)
		// repository.findByProgrammingLanguage("Java");

		// List<Person> persons = (List<Person>)
		// repository.buscarByProgrammingLanguage("Java");

		// List<Person> persons = (List<Person>)
		// repository.findByProgrammingLanguageAndName("Java", "Diego");

		List<Object[]> persons = repository.buscarPersonData();

		persons.stream().forEach(person -> System.out.println(person[0] + " es experto en " + person[1]));

		List<Object[]> personsValues = repository.buscarPersonDataByValues("Python", "Pepe");

		personsValues.stream().forEach(person -> System.out.println(person[0] + " es experto en " + person[1]));
	}

}
