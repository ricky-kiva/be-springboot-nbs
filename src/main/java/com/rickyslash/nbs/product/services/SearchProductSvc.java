package com.rickyslash.nbs.product.services;

import com.rickyslash.nbs.common.contract.Query;
import com.rickyslash.nbs.product.ProductRepository;
import com.rickyslash.nbs.product.model.Product;
import com.rickyslash.nbs.product.model.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductSvc implements Query<String, List<ProductDTO>> {
  private final ProductRepository productRepository;

  public SearchProductSvc(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ResponseEntity<List<ProductDTO>> execute(String name) {
    List<Product> products = productRepository.findByNameContaining(name);
    List<ProductDTO> productDTOs = products.stream().map(ProductDTO::new).toList();

    return ResponseEntity.ok(productDTOs);
  }
}
