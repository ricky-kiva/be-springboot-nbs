package com.rickyslash.nbs.common.exceptions.dto;

import lombok.Getter;

@Getter
public class ErrorResponse {
  private final String message;

  public ErrorResponse(String message) {
    this.message = message;
  }
}
