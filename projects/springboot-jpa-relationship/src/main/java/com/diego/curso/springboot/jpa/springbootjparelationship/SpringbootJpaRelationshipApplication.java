package com.diego.curso.springboot.jpa.springbootjparelationship;

// import java.util.ArrayList;
import java.util.Arrays;
// import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.diego.curso.springboot.jpa.springbootjparelationship.entities.Address;
import com.diego.curso.springboot.jpa.springbootjparelationship.entities.Client;
import com.diego.curso.springboot.jpa.springbootjparelationship.entities.Invoice;
import com.diego.curso.springboot.jpa.springbootjparelationship.repositories.ClientRepository;
import com.diego.curso.springboot.jpa.springbootjparelationship.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// manyToOneCreate();
		// manyToOneFindByIdClient();
		// oneToManyCreate();
		// oneToManyFindByIdClient();
		removeAddress();
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
			client.setAddresses(Arrays.asList(address1, address2));

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
