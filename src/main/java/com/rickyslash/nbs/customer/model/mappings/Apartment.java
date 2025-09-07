package com.rickyslash.nbs.customer.model.mappings;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "apartment")
@Data
public class Apartment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "street")
  private String street;

  @Column(name = "city")
  private String city;

  @Column(name = "state")
  private String state;
}
