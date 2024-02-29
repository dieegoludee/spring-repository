package com.diego.curso.springboot.jpa.springbootjpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.diego.curso.springboot.jpa.springbootjpa.dto.PersonDto;
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
		// update();
		// delete();
		// deletePerson();
		// personalizedQueries();
		// personalizedQueries2();
		// personalizedQueriesDistinct();
		// personalizedQueriesConcatUpperAndLowerCase();
		// personalizedQueriesBetween();
		queriesFunctionAggregation();
	}

	@Transactional(readOnly = true)
	public void queriesFunctionAggregation() {
		System.out.println("=============== Consulta total de registros de la tabla ===============");
		Long count = repository.getTotalPersons();
		System.out.println("Total persons: " + count);

		System.out.println("=============== Consulta con el valor mínimo del ID ===============");
		Long min = repository.getMinId();
		System.out.println("Min ID person: " + min);

		System.out.println("=============== Consulta con el valor máximo del ID ===============");
		Long max = repository.getMaxId();
		System.out.println("Max ID person: " + max);

		System.out.println("=============== Consulta con el nombre y el length del nombre ===============");
		List<Object[]> personList = repository.getPersonNameLength();
		personList.forEach(p -> System.out.println("Name: " + p[0] + " - Length: " + p[1]));

		System.out.println("=============== Consulta con el nombre mas corto ===============");
		Integer minLengthName = repository.getMinLengthName();
		System.out.println("Nombre más corto: " + minLengthName);

		System.out.println("=============== Consulta con el nombre mas largo ===============");
		Integer maxLengthName = repository.getMaxLengthName();
		System.out.println("Nombre más largo: " + maxLengthName);
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesBetween() {
		System.out.println("=============== Consulta personalizada con between ID ===============");
		// List<Person> persons = repository.findAllBetweenId(2L, 5L);
		List<Person> persons = repository.findByIdBetweenOrderByNameAsc(2L, 5L); // método sin anotación Query
		persons.forEach(System.out::println);

		System.out.println("=============== Consulta personalizada con between NAME ===============");
		// persons = repository.findAllBetweenName("J", "Q");
		persons = repository.findByNameBetweenOrderByNameDescLastnameAsc("J", "Q"); // método sin anotación Query
		persons.forEach(System.out::println);

		System.out.println("=============== Consulta personalizada con order by NAME ===============");
		persons = repository.findAllByOrderByNameAscLastnameDesc();
		persons.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesConcatUpperAndLowerCase() {
		System.out.println("=============== Consultas con nombres y apellidos de personas ===============");
		List<String> names = repository.findAllFullNameConcat();
		names.forEach(System.out::println);

		System.out.println("=============== Consultas con nombres y apellidos de personas Lower ===============");
		names = repository.findAllFullNameConcatLower();
		names.forEach(System.out::println);

		System.out.println("=============== Consultas con nombres y apellidos de personas Upper ===============");
		names = repository.findAllFullNameConcatUpper();
		names.forEach(System.out::println);

		System.out.println("=============== Consultas personalizada persona upper y lower case ===============");
		List<Object[]> personReg = repository.getPersonDataListCase();
		personReg.forEach(
				person -> System.out.println("id=" + person[0] + ", name=" + person[1] + ", lastname=" + person[2]
						+ ", programmingLanguage=" + person[3]));
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct() {
		System.out.println("=============== Consultas con nombres de personas sin Distinct ===============");
		List<String> names = repository.findAllNames();
		names.forEach(System.out::println);

		System.out.println("=============== Consultas con nombres de personas con Distinct ===============");
		names = repository.findAllNamesDistinct();
		names.forEach(System.out::println);

		System.out.println("=============== Consultas con nombres de lenguajes con Distinct ===============");
		List<String> languages = repository.findAllLanguagesDistinct();
		languages.forEach(System.out::println);

		System.out.println("=============== Consulta con número de lenguajes con Distinct y Count ===============");
		Long languagesCount = repository.findAllLanguagesDistinctCount();
		System.out.println(languagesCount + " lenguajes diferentes totales");
	}

	@Transactional(readOnly = true)
	public void personalizedQueries2() {
		System.out
				.println(
						"============================== Consulta por objeto persona y lenguaje de programación ==============================");
		List<Object[]> personsRegs = repository.findAllMixPerson();

		personsRegs.forEach(reg -> {
			System.out.println("programmingLanguage=" + reg[1] + ", person=" + reg[0]);
		});

		System.out.println("Consulta que puebla y devuelve objeto entity de una instancia personalizada");
		List<Person> persons = repository.findAllObjectPersonPersonalized();
		persons.forEach(System.out::println);

		System.out.println("Consulta que puebla y devuelve objeto dto de una instancia personalizada");
		List<PersonDto> personsDto = repository.findAllPersonDto();
		personsDto.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizedQueries() {
		Scanner sc = new Scanner(System.in);
		System.out
				.println("============================== Consulta solo el nombre por el id ==============================");
		System.out.println("Ingrese el ID: ");
		Long id = sc.nextLong();

		System.out.println("===== mostrando solo el nombre =====");
		String name = repository.getNameById(id);
		System.out.println(name);

		System.out.println("===== mostrando solo el id =====");
		Long idDb = repository.getIdById(id);
		System.out.println(idDb);

		System.out.println("===== mostrando nombre completo con concat =====");
		String fullname = repository.getFullNameById(id);
		System.out.println(fullname);

		System.out.println("===== consulta por campos personalizados por el id =====");
		Optional<Object> optionalReg = repository.buscarPersonDataById(id);
		if (optionalReg.isPresent()) {
			Object[] personReg = (Object[]) optionalReg.orElseThrow();
			System.out.println("id=" + personReg[0] + ", name=" + personReg[1] + ", lastname=" + personReg[2]
					+ ", programmingLanguage=" + personReg[3]);
		}

		System.out.println("===== consulta por campos personalizados lista =====");
		List<Object[]> regs = repository.buscarPersonDataList();
		regs.forEach(reg -> System.out.println("id=" + reg[0] + ", name=" + reg[1] + ", lastname=" + reg[2]
				+ ", programmingLanguage=" + reg[3]));

		sc.close();
	}

	@Transactional
	public void deletePerson() {
		repository.findAll().forEach(System.out::println);

		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el ID a eliminar: ");
		Long id = sc.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);
		// optionalPerson.ifPresentOrElse(person -> repository.delete(person),
		// () -> System.out.println("No existe la persona con ese id"));

		optionalPerson.ifPresentOrElse(repository::delete,
				() -> System.out.println("No existe la persona con ese id"));

		repository.findAll().forEach(System.out::println);
		sc.close();
	}

	@Transactional
	public void delete() {
		repository.findAll().forEach(System.out::println);

		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el ID a eliminar: ");
		Long id = sc.nextLong();
		repository.deleteById(id);

		repository.findAll().forEach(System.out::println);
		sc.close();
	}

	@Transactional
	public void update() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona: ");
		Long id = sc.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresent(personDb -> {
			System.out.println(personDb);
			System.out.println("Ingrese el nuevo nombre: ");
			String name = sc.next();
			personDb.setName(name);

			Person personUpdated = repository.save(personDb);
			System.out.println(personUpdated);
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
