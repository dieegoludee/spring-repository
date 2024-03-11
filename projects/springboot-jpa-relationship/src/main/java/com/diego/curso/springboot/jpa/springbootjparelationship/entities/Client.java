package com.diego.curso.springboot.jpa.springbootjparelationship.entities;

// import java.util.ArrayList;
import java.util.HashSet;
// import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "clients")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String lastname;

  // @JoinColumn(name = "client_id") // Foreign Key de la tabla addresses
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinTable(name = "tbl_clientes_to_direcciones", joinColumns = @JoinColumn(name = "id_cliente"), inverseJoinColumns = @JoinColumn(name = "id_direcciones"), uniqueConstraints = @UniqueConstraint(columnNames = {
      "id_direcciones" }))
  private Set<Address> addresses;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
  private Set<Invoice> invoices;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_cliente_detalle")
  private ClientDetails clientDetails;

  public Client() {
    addresses = new HashSet<>();
    invoices = new HashSet<>();
  }

  public Client(String name, String lastname) {
    this(); // Llama al constructor vacío e inicializa las direcciones
    this.name = name;
    this.lastname = lastname;
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

  public Set<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(Set<Address> addresses) {
    this.addresses = addresses;
  }

  public Set<Invoice> getInvoices() {
    return invoices;
  }

  public void setInvoices(Set<Invoice> invoices) {
    this.invoices = invoices;
  }

  public Client addInvoice(Invoice invoice) {
    invoices.add(invoice);
    // Al ser bidireccional hay que pasar a la factura el cliente
    invoice.setClient(this); // con this hacemos referencia a la propia instancia de la clase
    return this; // para encadenar devolvemos el mismo objeto y asi optimizamos el método
  }

  public void removeInvoice(Invoice invoice) {
    this.getInvoices().remove(invoice);
    invoice.setClient(null); // null para borrar el cliente
  }

  public ClientDetails getClientDetails() {
    return clientDetails;
  }

  public void setClientDetails(ClientDetails clientDetails) {
    this.clientDetails = clientDetails;
  }

  @Override
  public String toString() {
    return "Client {id=" + id +
        ", name=" + name +
        ", lastname=" + lastname +
        ", invoices=" + invoices +
        ", addresses=" + addresses +
        ", clientDetails=" + clientDetails +
        "}";
  }

}
