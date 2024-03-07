package com.diego.curso.springboot.jpa.springbootjparelationship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.diego.curso.springboot.jpa.springbootjparelationship.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

  @Query("select c from Client c left join fetch c.addresses where c.id=?1") // Devuelve el objeto con todas las
                                                                             // direcciones en una sola consulta con
                                                                             // join fetch
  Optional<Client> findOneWithAddresses(Long id);

  @Query("select c from Client c left join fetch c.invoices where c.id=?1")
  Optional<Client> findOneWithInvoices(Long id);

  @Query("select c from Client c left join fetch c.invoices left join fetch c.addresses where c.id=?1")
  Optional<Client> findOne(Long id);

}
