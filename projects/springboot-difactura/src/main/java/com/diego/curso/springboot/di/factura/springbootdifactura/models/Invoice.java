package com.diego.curso.springboot.di.factura.springbootdifactura.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Invoice {

  @Autowired
  private Client client;

  @Value("${invoice.description}") // Hace falta cambiarlo dependiendo del bean (invoice.description.office)
  private String description;

  // @Qualifier("itemsInvoiceOffice")
  @Autowired
  @Qualifier("default")
  private List<Item> items;

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public int getTotal() {
    // int total = 0;
    // for (Item item : items) {
    // total += item.getImporte();
    // }

    // int total = items.stream()
    // .map(item -> item.getImporte())
    // .reduce(0, (sum, importe) -> sum + importe);

    return items.stream()
        .map(item -> item.getImporte())
        .reduce(0, (sum, importe) -> sum + importe);
  }

}
