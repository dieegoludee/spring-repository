package com.diego.curso.springboot.jpa.springbootjparelationship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.diego.curso.springboot.jpa.springbootjparelationship.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

  @Query("select c from Client c join fetch c.addresses") // Devuelve el objeto con todas las direcciones en una sola
                                                          // consulta con join fetch
  Optional<Client> findOne(Long id);

}
