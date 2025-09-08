package com.rickyslash.nbs.domain.appuser.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "app_user")
@Data
public class AppUser {
  @Id
  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;
}
