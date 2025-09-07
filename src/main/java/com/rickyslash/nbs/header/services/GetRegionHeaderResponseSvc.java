package com.rickyslash.nbs.header.services;

import com.rickyslash.nbs.common.contract.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetRegionHeaderResponseSvc implements Query<String, String> {
  @Override
  public ResponseEntity<String> execute(String region) {
    String res = null;

    if (region.equals("ID")) {
      res = "Rayuan Pulau Kelapa";
    } else if (region.equals("US")) {
      res = "BALD EAGLE FREEDOM";
    } else if (region.equals("CAN")) {
      res = "MAPLE SYRUP";
    } else {
      res = "Country not supported";
    }

    return ResponseEntity.ok(res);
  }
}
