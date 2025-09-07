package com.rickyslash.nbs.customer.model;

import com.rickyslash.nbs.customer.model.mappings.HomeDTO;
import com.rickyslash.nbs.customer.model.mappings.PropertyDTO;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {
  private String firstName;
  private String lastName;
  private HomeDTO home;
  private List<PropertyDTO> properties;

  public CustomerDTO(Customer customer) {
    this.firstName = customer.getFirstname();
    this.lastName = customer.getLastname();
    this.home = (customer.getHome() != null)
        ? new HomeDTO(customer.getHome())
        : null;

    this.properties = (customer.getProperties() != null)
        ? customer.getProperties().stream().map(PropertyDTO::new).toList()
        : null;
  }
}
