package com.rickyslash.nbs.product.services;

import com.rickyslash.nbs.common.contract.Query;
import com.rickyslash.nbs.product.ProductRepository;
import com.rickyslash.nbs.product.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetProductsSvc implements Query<Void, List<Product>> {
  private final ProductRepository productRepository;

  public GetProductsSvc(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ResponseEntity<List<Product>> execute(Void input) {
    List<Product> products = productRepository.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(products);
  }
}
