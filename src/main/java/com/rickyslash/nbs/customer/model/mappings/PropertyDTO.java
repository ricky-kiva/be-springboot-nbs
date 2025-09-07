package com.rickyslash.nbs.customer.model.mappings;

import lombok.Data;

@Data
public class PropertyDTO {
  private String street;
  private String city;
  private String state;

  public PropertyDTO(Property property) {
    this.street = property.getStreet();
    this.city = property.getCity();
    this.state = property.getState();
  }
}
