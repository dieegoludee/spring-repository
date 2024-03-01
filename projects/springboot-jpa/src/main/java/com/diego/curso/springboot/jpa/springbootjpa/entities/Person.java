package com.diego.curso.springboot.jpa.springbootjpa.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String lastname;

  @Column(name = "programming_language")
  private String programmingLanguage;

  @Column(name = "create_at")
  private LocalDateTime createAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public Person() {

  }

  public Person(String name, String lastname) {
    this.name = name;
    this.lastname = lastname;
  }

  public Person(Long id, String name, String lastname, String programmingLanguage) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.programmingLanguage = programmingLanguage;
  }

  @PrePersist
  public void prePersist() {
    System.out.println("Evento del ciclo de vida del Entity pre-persist");
    this.createAt = LocalDateTime.now();
  }

  @PreUpdate
  public void preUpdate() {
    System.out.println("Evento del ciclo de vida del Entity pre-update");
    this.updatedAt = LocalDateTime.now();

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getProgrammingLanguage() {
    return programmingLanguage;
  }

  public void setProgrammingLanguage(String programmingLanguage) {
    this.programmingLanguage = programmingLanguage;
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", name=" + name + ", lastname=" + lastname + ", programmingLanguage="
        + programmingLanguage + ", createAt=" + createAt + ", updatedAt=" + updatedAt + "]";
  }

}
