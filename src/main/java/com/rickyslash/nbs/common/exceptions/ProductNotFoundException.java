package com.rickyslash.nbs.common.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Slf4j
public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException() {
    super(ErrorMessages.PRODUCT_NOT_FOUND.getMessage());
    log.error("Exception {} thrown", getClass().getSimpleName());
  }
}
