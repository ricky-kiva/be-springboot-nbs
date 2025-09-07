package com.rickyslash.nbs.common.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessages {
  PRODUCT_NOT_FOUND("Product not found"),
  CUSTOMER_NOT_FOUND("Customer not found");

  private final String message;

  ErrorMessages(String message) {
    this.message = message;
  }
}
