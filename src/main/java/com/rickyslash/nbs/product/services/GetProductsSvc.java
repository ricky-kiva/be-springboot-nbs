package com.rickyslash.nbs.product.services;

import com.rickyslash.nbs.common.contract.Query;
import com.rickyslash.nbs.product.ProductRepository;
import com.rickyslash.nbs.product.model.Product;
import com.rickyslash.nbs.product.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetProductsSvc implements Query<Void, List<ProductDTO>> {
  private final ProductRepository productRepository;

  public GetProductsSvc(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ResponseEntity<List<ProductDTO>> execute(Void input) {
    List<Product> products = productRepository.findAll();
    List<ProductDTO> productDTOs = products.stream().map(ProductDTO::new).toList();

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(productDTOs);
  }
}
