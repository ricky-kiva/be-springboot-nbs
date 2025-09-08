package com.rickyslash.nbs.domain.header.services;

import com.rickyslash.nbs.common.contract.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetRegionHeaderResponseSvc implements Query<String, String> {
  @Override
  public ResponseEntity<String> execute(String region) {
    String res = switch (region) {
      case "ID" -> "Rayuan Pulau Kelapa";
      case "US" -> "BALD EAGLE FREEDOM";
      case "CAN" -> "MAPLE SYRUP";
      default -> "Country not supported";
    };

    return ResponseEntity.ok(res);
  }
}
