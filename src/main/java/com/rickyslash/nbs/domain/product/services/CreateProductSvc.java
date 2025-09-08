package com.rickyslash.nbs.domain.product.services;

import com.rickyslash.nbs.common.contract.Command;
import com.rickyslash.nbs.domain.product.ProductRepository;
import com.rickyslash.nbs.domain.product.model.Product;
import com.rickyslash.nbs.domain.product.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductSvc implements Command<Product, ProductDTO> {
  private final ProductRepository productRepository;

  public CreateProductSvc(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ResponseEntity<ProductDTO> execute(Product product) {
    Product savedProduct = productRepository.save(product);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new ProductDTO(savedProduct));
  }
}
