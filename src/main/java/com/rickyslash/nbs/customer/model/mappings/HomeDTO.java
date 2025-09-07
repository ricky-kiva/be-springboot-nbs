package com.rickyslash.nbs.customer.model.mappings;

import lombok.Data;

@Data
public class HomeDTO {
  private String street;
  private String city;
  private String state;

  public HomeDTO(Home home) {
    this.street = home.getStreet();
    this.city = home.getCity();
    this.state = home.getState();
  }
}
