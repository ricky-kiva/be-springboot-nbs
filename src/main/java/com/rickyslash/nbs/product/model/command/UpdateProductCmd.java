package com.rickyslash.nbs.product.model.command;

import com.rickyslash.nbs.product.model.Product;
import lombok.Data;

@Data
public class UpdateProductCmd {
  private Integer id;
  private Product product;

  public UpdateProductCmd(Integer id, Product product) {
    this.id = id;
    this.product = product;
  }
}
