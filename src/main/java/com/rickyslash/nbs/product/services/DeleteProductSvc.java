package com.rickyslash.nbs.product.services;

import com.rickyslash.nbs.common.contract.Command;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductSvc implements Command<Void, String> {
  @Override
  public ResponseEntity<String> execute(Void input) {
    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .body("Product deleted");
  }
}
