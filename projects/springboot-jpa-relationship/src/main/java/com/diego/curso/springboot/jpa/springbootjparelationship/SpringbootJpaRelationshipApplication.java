package com.diego.curso.springboot.jpa.springbootjparelationship;

// import java.util.ArrayList;
// import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
// import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.diego.curso.springboot.jpa.springbootjparelationship.entities.Address;
import com.diego.curso.springboot.jpa.springbootjparelationship.entities.Client;
import com.diego.curso.springboot.jpa.springbootjparelationship.entities.ClientDetails;
import com.diego.curso.springboot.jpa.springbootjparelationship.entities.Course;
import com.diego.curso.springboot.jpa.springbootjparelationship.entities.Invoice;
import com.diego.curso.springboot.jpa.springbootjparelationship.entities.Student;
import com.diego.curso.springboot.jpa.springbootjparelationship.repositories.ClientDetailsRepository;
import com.diego.curso.springboot.jpa.springbootjparelationship.repositories.ClientRepository;
import com.diego.curso.springboot.jpa.springbootjparelationship.repositories.CourseRepository;
import com.diego.curso.springboot.jpa.springbootjparelationship.repositories.InvoiceRepository;
import com.diego.curso.springboot.jpa.springbootjparelationship.repositories.StudentRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// manyToOneCreate();
		// manyToOneFindByIdClient();
		// oneToManyCreate();
		// oneToManyFindByIdClient();
		// removeAddress();
		// removeAddressFindById();
		// oneToManyInvoiceBidireccional();
		// oneToManyInvoiceBidireccionalFindById();
		// removeInvoiceBidireccionalFindById();
		// removeInvoiceBidireccional();
		// oneToOne();
		// oneToOneFindById();
		// oneToOneBidireccional();
		// oneToOneBidireccionalFindById();
		// manyToMany();
		// manyToManyFind();
		// manyToManyRemoveFind();
		// manyToManyRemove();
		// manyToManyBidireccional();
		// manyToManyBidireccionalRemove();
		// manyToManyBidireccionalFind();
		manyToManyRemoveBidireccionalFind();
	}

	@SuppressWarnings("null")
	@Transactional
	public void manyToManyRemoveBidireccionalFind() {
		Student student1 = studentRepository.findOneWithCourses(1L).orElseThrow();
		Student student2 = studentRepository.findOneWithCourses(2L).orElseThrow();

		Course course1 = courseRepository.findOneWithStudents(1L).orElseThrow();
		Course course2 = courseRepository.findOneWithStudents(2L).orElseThrow();

		// student1.setCourses(Set.of(course1, course2)); // Creamos un Set para los 2
		// cursos
		// student2.setCourses(Set.of(course2)); // Creamos un Set para 1 curso

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);
		if (studentOptionalDb.isPresent()) {
			Student studentDb = studentOptionalDb.orElseThrow();
			Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(1L);

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.orElseThrow();
				studentDb.removeCourse(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}
	}

	@SuppressWarnings("null")
	@Transactional
	public void manyToManyBidireccionalFind() {
		Student student1 = studentRepository.findOneWithCourses(1L).orElseThrow();
		Student student2 = studentRepository.findOneWithCourses(2L).orElseThrow();

		Course course1 = courseRepository.findOneWithStudents(1L).orElseThrow();
		Course course2 = courseRepository.findOneWithStudents(2L).orElseThrow();

		// student1.setCourses(Set.of(course1, course2)); // Creamos un Set para los 2
		// cursos
		// student2.setCourses(Set.of(course2)); // Creamos un Set para 1 curso

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);
	}

	@SuppressWarnings("null")
	@Transactional
	public void manyToManyBidireccionalRemove() {
		Student student1 = new Student("Diego", "Martin");
		Student student2 = new Student("Jano", "Suarez");

		Course course1 = new Course("Curso de Spring Boot 3", "Andres");
		Course course2 = new Course("Curso de Java", "Andres");

		// student1.setCourses(Set.of(course1, course2)); // Creamos un Set para los 2
		// cursos
		// student2.setCourses(Set.of(course1)); // Creamos un Set para 1 curso

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
		if (studentOptionalDb.isPresent()) {
			Student studentDb = studentOptionalDb.orElseThrow();
			Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(3L);

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.orElseThrow();
				studentDb.removeCourse(courseDb);
				;

				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}
	}

	@SuppressWarnings("null")
	@Transactional
	public void manyToManyBidireccional() {
		Student student1 = new Student("Diego", "Martin");
		Student student2 = new Student("Jano", "Suarez");

		Course course1 = new Course("Curso de Spring Boot 3", "Andres");
		Course course2 = new Course("Curso de Java", "Andres");

		// student1.setCourses(Set.of(course1, course2)); // Creamos un Set para los 2
		// cursos
		// student2.setCourses(Set.of(course1)); // Creamos un Set para 1 curso

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);

	}

	@SuppressWarnings("null")
	@Transactional
	public void manyToManyRemove() {
		Student student1 = new Student("Diego", "Martin");
		Student student2 = new Student("Jano", "Suarez");

		Course course1 = new Course("Curso de Spring Boot 3", "Andres");
		Course course2 = new Course("Curso de Java", "Andres");

		student1.setCourses(Set.of(course1, course2)); // Creamos un Set para los 2 cursos
		student2.setCourses(Set.of(course1)); // Creamos un Set para 1 curso

		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
		if (studentOptionalDb.isPresent()) {
			Student studentDb = studentOptionalDb.orElseThrow();
			Optional<Course> courseOptionalDb = courseRepository.findById(3L);

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.orElseThrow();
				studentDb.getCourses().remove(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}
	}

	@SuppressWarnings("null")
	@Transactional
	public void manyToManyRemoveFind() {
		Student student1 = studentRepository.findById(1L).orElseThrow(); // buscamos y obtenemos estudiante con id 1
		Student student2 = studentRepository.findById(2L).orElseThrow(); // buscamos y obtenemos estudiante con id 2

		Course course1 = courseRepository.findById(1L).orElseThrow(); // buscamos y obtenemos curso con id 1
		Course course2 = courseRepository.findById(2L).orElseThrow(); // buscamos y obtenemos curso con id 2

		student1.setCourses(Set.of(course1, course2)); // Creamos un Set para los 2 cursos
		student2.setCourses(Set.of(course2)); // Creamos un Set para 1 curso

		studentRepository.saveAll(Set.of(student1, student2)); // Creamos un set y añadimos los cursos
		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);
		if (studentOptionalDb.isPresent()) {
			Student studentDb = studentOptionalDb.orElseThrow();
			Optional<Course> courseOptionalDb = courseRepository.findById(2L);

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.orElseThrow();
				studentDb.getCourses().remove(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}
	}

	@SuppressWarnings("null")
	@Transactional
	public void manyToManyFind() {
		Optional<Student> studentOpt1 = studentRepository.findById(1L);
		Optional<Student> studentOpt2 = studentRepository.findById(2L);

		Student student1 = studentOpt1.orElseThrow(); // recibimos el estudiante
		Student student2 = studentOpt2.orElseThrow();

		Course course1 = courseRepository.findById(1L).orElseThrow();
		Course course2 = courseRepository.findById(2L).orElseThrow();

		student1.setCourses(Set.of(course1, course2)); // Creamos un Set para los 2 cursos
		student2.setCourses(Set.of(course2)); // Creamos un Set para 1 curso

		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);
	}

	@SuppressWarnings("null")
	@Transactional
	public void manyToMany() {
		Student student1 = new Student("Diego", "Martin");
		Student student2 = new Student("Jano", "Suarez");

		Course course1 = new Course("Curso de Spring Boot 3", "Andres");
		Course course2 = new Course("Curso de Java", "Andres");

		student1.setCourses(Set.of(course1, course2)); // Creamos un Set para los 2 cursos
		student2.setCourses(Set.of(course1)); // Creamos un Set para 1 curso

		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void oneToOneBidireccionalFindById() {

		Optional<Client> optionalClient = clientRepository.findOne(1L);
		optionalClient.ifPresent(client -> {
			ClientDetails clientDetails = new ClientDetails(true, 5000);
			client.setClientDetails(clientDetails);
			clientRepository.save(client);

			System.out.println(client);
		});
	}

	@Transactional
	public void oneToOneBidireccional() {

		Client client = new Client("Erba", "Pura");
		ClientDetails clientDetails = new ClientDetails(true, 5000);

		client.setClientDetails(clientDetails);

		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void oneToOneFindById() {

		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Optional<Client> clientOptional = clientRepository.findOne(2L); // new Client("Erba", "Pura");
		clientOptional.ifPresent(client -> {
			client.setClientDetails(clientDetails);
			clientRepository.save(client);

			System.out.println(client);
		});

	}

	@Transactional
	public void oneToOne() {

		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Client client = new Client("Erba", "Pura");
		client.setClientDetails(clientDetails);
		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void removeInvoiceBidireccional() {
		Client client = new Client("Fran", "Moras");

		Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("Compras de la oficina", 8000L);

		client.addInvoice(invoice1).addInvoice(invoice2);

		clientRepository.save(client);
		System.out.println(client);

		Optional<Client> optionalClientDb = clientRepository.findOne(3L);
		optionalClientDb.ifPresent(clientDb -> {
			// Invoice invoice3 = new Invoice("Compras de la casa", 3000L);
			// invoice3.setId(1L);

			Optional<Invoice> invoiceOptional = invoiceRepository.findById(2L); // Optional.of(invoice3);
			invoiceOptional.ifPresent(invoice -> {
				clientDb.removeInvoice(invoice);

				clientRepository.save(clientDb);
				System.out.println(clientDb);
			});
		});
	}

	@Transactional
	public void removeInvoiceBidireccionalFindById() {
		Optional<Client> optionalClient = clientRepository.findOne(1L);
		optionalClient.ifPresent(client -> {
			Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("Compras de la oficina", 8000L);

			client.addInvoice(invoice1).addInvoice(invoice2);

			clientRepository.save(client);
			System.out.println(client);
		});

		Optional<Client> optionalClientDb = clientRepository.findOne(1L);
		optionalClientDb.ifPresent(client -> {
			Optional<Invoice> invoiceOptional = invoiceRepository.findById(2L);
			invoiceOptional.ifPresent(invoice -> {
				client.removeInvoice(invoice);

				clientRepository.save(client);
				System.out.println(client);
			});
		});
	}

	@Transactional
	public void oneToManyInvoiceBidireccionalFindById() {
		Optional<Client> optionalClient = clientRepository.findOne(1L);
		optionalClient.ifPresent(client -> {
			Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("Compras de la oficina", 8000L);

			client.addInvoice(invoice1).addInvoice(invoice2);

			clientRepository.save(client);
			System.out.println(client);
		});
	}

	@Transactional
	public void oneToManyInvoiceBidireccional() {
		Client client = new Client("Fran", "Moras");

		Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("Compras de la oficina", 8000L);

		// client.setInvoices(Arrays.asList(invoice1, invoice2));
		// // Al ser bidireccional hay que pasar a la factura el cliente
		// invoice1.setClient(client);
		// invoice2.setClient(client);
		// Hace lo mismo, pero queda más limpio y optimizado
		client.addInvoice(invoice1).addInvoice(invoice2);

		clientRepository.save(client);
		System.out.println();

	}

	@Transactional
	public void removeAddressFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);

		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El Verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);

			client.setAddresses(addresses);

			clientRepository.save(client);
			System.out.println(client);

			// Eliminando una dirección
			Optional<Client> optionalClient2 = clientRepository.findOneWithAddresses(2L);
			// System.out.println(optionalClient2);

			optionalClient2.ifPresent(c -> {
				// Recogemos la dirección con id 2
				@SuppressWarnings("unchecked")
				Address address = ((List<Address>) c.getAddresses()).get(1);
				c.getAddresses().remove(address);
				clientRepository.save(c);
				System.out.println(c);
				System.out.println(optionalClient2);
			});
		});

	}

	@Transactional
	public void removeAddress() {
		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El Verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);
		System.out.println(client);

		// Eliminando una dirección
		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(cli -> {
			cli.getAddresses().remove(address1);
			clientRepository.save(cli);
			System.out.println(cli);
		});

	}

	@Transactional
	public void oneToManyFindByIdClient() {
		Optional<Client> optionalClient = clientRepository.findById(2L);

		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El Verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			// List<Address> list = new ArrayList<>();
			// list.add(address1);
			// list.add(address2);
			// client.setAddresses(list);
			// ES LO MISMO, PERO MÁS REDUCIDO

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);

			clientRepository.save(client);
			System.out.println(client);
		});

	}

	@Transactional
	public void oneToManyCreate() {
		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El Verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);
		System.out.println(client);

	}

	@Transactional
	public void manyToOneCreate() {
		Client client = new Client("John", "Doe");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Compras de oficina", 2000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}

	@Transactional
	public void manyToOneFindByIdClient() {
		Optional<Client> optionalClient = clientRepository.findById(1L);

		if (optionalClient.isPresent()) {
			Client client = optionalClient.orElseThrow();

			Invoice invoice = new Invoice("Compras de oficina", 2000L);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
		}

	}

}
