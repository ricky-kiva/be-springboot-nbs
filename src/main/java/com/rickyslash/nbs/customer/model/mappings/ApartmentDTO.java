package com.rickyslash.nbs.customer.model.mappings;

public class ApartmentDTO {
  private String street;
  private String city;
  private String state;

  public ApartmentDTO(Apartment apartment) {
    this.street = apartment.getStreet();
    this.city = apartment.getCity();
    this.state = apartment.getState();
  }
}
