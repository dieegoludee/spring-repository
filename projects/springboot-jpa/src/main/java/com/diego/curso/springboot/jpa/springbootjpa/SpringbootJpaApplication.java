package com.diego.curso.springboot.jpa.springbootjpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

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
		// findOne();
		// create();
		update();
	}

	@Transactional
	public void update() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona: ");
		Long id = sc.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresent(person -> {
			System.out.println(person);
			System.out.println("Ingrese el nuevo nombre: ");
			String name = sc.next();
			person.setName(name);

			Person personDb = repository.save(person);
			System.out.println(personDb);
		});

		sc.close();
	}

	@Transactional
	public void create() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el nombre: ");
		String name = sc.next();
		System.out.println("Ingrese el apellido: ");
		String lastname = sc.next();
		System.out.println("Ingrese el lenguaje de programación: ");
		String programmingLanguage = sc.next();
		sc.close();

		// Dejamos el id en null, ya que es auto-increment
		Person person = new Person(null, name, lastname, programmingLanguage);
		Person newPerson = repository.save(person);

		System.out.println(newPerson);
		repository.findById(newPerson.getId()).ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
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

	@Transactional(readOnly = true)
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
