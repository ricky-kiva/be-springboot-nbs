package com.rickyslash.nbs.domain.header.services;

import com.rickyslash.nbs.common.contract.Query;
import com.rickyslash.nbs.domain.product.model.Product;
import com.rickyslash.nbs.domain.product.model.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetProductWithHeaderSvc implements Query<Void, ProductDTO> {
  @Override
  public ResponseEntity<ProductDTO> execute(Void input) {
    Product product = new Product();

    product.setId(250907);
    product.setName("Super Great Product");
    product.setDescription("The greatest product you'll ever see ever");
    product.setPrice(9.99);

    return ResponseEntity.ok(new ProductDTO(product));
  }
}
