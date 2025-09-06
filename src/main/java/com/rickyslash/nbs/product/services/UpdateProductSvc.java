package com.rickyslash.nbs.product.services;

import com.rickyslash.nbs.common.contract.Command;
import com.rickyslash.nbs.product.ProductRepository;
import com.rickyslash.nbs.product.model.Product;
import com.rickyslash.nbs.product.model.ProductDTO;
import com.rickyslash.nbs.product.model.command.UpdateProductCmd;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductSvc implements Command<UpdateProductCmd, ProductDTO> {
  private final ProductRepository productRepository;

  public UpdateProductSvc(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ResponseEntity<ProductDTO> execute(UpdateProductCmd command) {
    Optional<Product> productOptional = productRepository.findById(command.getId());

    if (productOptional.isPresent()) {
      Product product = command.getProduct();

      product.setId(command.getId());

      productRepository.save(product);

      return ResponseEntity.ok(new ProductDTO(product));
    }

    return null;
  }
}
