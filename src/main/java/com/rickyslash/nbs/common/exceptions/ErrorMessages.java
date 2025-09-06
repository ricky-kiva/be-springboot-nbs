package com.rickyslash.nbs.common.exceptions;

import lombok.Getter;

public enum ErrorMessages {
  PRODUCT_NOT_FOUND("Product not found");

  @Getter
  private final String message;

  ErrorMessages(String message) {
    this.message = message;
  }
}
