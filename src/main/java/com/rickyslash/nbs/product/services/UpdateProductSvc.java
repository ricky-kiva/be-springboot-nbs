package com.rickyslash.nbs.product.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductSvc {
  public ResponseEntity<String> execute() {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body("Product updated");
  }
}
