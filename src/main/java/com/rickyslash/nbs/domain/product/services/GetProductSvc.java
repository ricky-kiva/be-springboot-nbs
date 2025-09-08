package com.rickyslash.nbs.domain.product.services;

import com.rickyslash.nbs.common.contract.Query;
import com.rickyslash.nbs.common.exceptions.ProductNotFoundException;
import com.rickyslash.nbs.domain.product.ProductRepository;
import com.rickyslash.nbs.domain.product.model.Product;
import com.rickyslash.nbs.domain.product.model.ProductDTO;
import org.springframework.cache.annotation.Cacheable;
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
  @Cacheable("productCache")
  public ResponseEntity<ProductDTO> execute(Integer input) {
    Optional<Product> productOptional = productRepository.findById(input);

    if (productOptional.isPresent()) {
      return ResponseEntity.ok(new ProductDTO(productOptional.get()));
    }

    throw new ProductNotFoundException();
  }
}
