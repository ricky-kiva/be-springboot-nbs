package com.rickyslash.nbs.customer.model;

import com.rickyslash.nbs.customer.model.mappings.HomeDTO;
import lombok.Data;

@Data
public class CustomerDTO {
  private String firstName;
  private String lastName;
  private HomeDTO home;

  public CustomerDTO(Customer customer) {
    this.firstName = customer.getFirstname();
    this.lastName = customer.getLastname();
    this.home = (customer.getHome() != null)
      ? new HomeDTO(customer.getHome())
      : null;
  }
}
