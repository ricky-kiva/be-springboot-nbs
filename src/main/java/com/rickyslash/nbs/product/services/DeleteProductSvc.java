package com.rickyslash.nbs.product.services;

import com.rickyslash.nbs.common.contract.Command;
import com.rickyslash.nbs.product.ProductRepository;
import com.rickyslash.nbs.product.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductSvc implements Command<Integer, Void> {
  private final ProductRepository productRepository;

  public DeleteProductSvc(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ResponseEntity<Void> execute(Integer id) {
    Optional<Product> productOptional = productRepository.findById(id);

    if (productOptional.isPresent()) {
      productRepository.deleteById(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    return null;
  }
}
