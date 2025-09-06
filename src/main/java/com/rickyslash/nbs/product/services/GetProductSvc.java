package com.rickyslash.nbs.product.services;

import com.rickyslash.nbs.common.contract.Query;
import com.rickyslash.nbs.product.ProductRepository;
import com.rickyslash.nbs.product.model.Product;
import com.rickyslash.nbs.product.model.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductSvc implements Query<Integer, ProductDTO> {
  private final ProductRepository productRepository;

  public GetProductSvc(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ResponseEntity<ProductDTO> execute(Integer input) {
    Optional<Product> productOptional = productRepository.findById(input);

    if (productOptional.isPresent()) {
      return ResponseEntity.ok(new ProductDTO(productOptional.get()));
    }

    return null;
  }
}
