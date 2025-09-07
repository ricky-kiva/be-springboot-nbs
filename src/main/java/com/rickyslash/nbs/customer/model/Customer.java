package com.rickyslash.nbs.customer.model;

import com.rickyslash.nbs.customer.model.mappings.Home;
import jakarta.persistence.*;
import lombok.Data;

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
}
