package com.rickyslash.nbs.customer.model;

import com.rickyslash.nbs.customer.model.mappings.Home;
import com.rickyslash.nbs.customer.model.mappings.Property;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "customer")
@Data
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "first_name")
  private String firstname;

  @Column(name = "last_name")
  private String lastname;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "home_id")
  private Home home;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "customer_id")
  private List<Property> properties;
}
