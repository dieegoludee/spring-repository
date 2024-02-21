package com.diego.curso.springboot.jpa.springbootjpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.diego.curso.springboot.jpa.springbootjpa.entities.Person;
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

		// list();
		findOne();
	}

	public void findOne() {
		// Person person = null;
		// Optional<Person> optionalPerson = repository.findById(1L);
		// // if (!optionalPerson.isEmpty())
		// if (optionalPerson.isPresent())
		// person = optionalPerson.get();

		// System.out.println(person);

		// repository.findById(1L).ifPresent(person -> System.out.println(person));
		// // con la función Lambda simplificada, pero devuelve lo mismo
		// repository.findById(1L).ifPresent(System.out::println);

		// repository.findOne(1L).ifPresent(System.out::println);
		// repository.findOneName("Pepe").ifPresent(System.out::println);
		repository.findOneLikeName("ie").ifPresent(System.out::println); // ie para buscar Diego con like
		repository.findByNameContaining("ep").ifPresent(System.out::println); // ep para buscar Pepe con like
	}

	public void list() {
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
