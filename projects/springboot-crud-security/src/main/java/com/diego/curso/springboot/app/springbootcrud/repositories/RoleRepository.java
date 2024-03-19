package com.diego.curso.springboot.app.springbootcrud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.diego.curso.springboot.app.springbootcrud.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
