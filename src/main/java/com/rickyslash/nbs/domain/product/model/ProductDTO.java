package com.rickyslash.nbs.domain.product.model;

import lombok.Data;

@Data
public class ProductDTO {
  private int id;
  private String name;
  private String description;

  public ProductDTO(Product product) {
    this.id = product.getId();
    this.name = product.getName();
    this.description = product.getDescription();
  }
}
