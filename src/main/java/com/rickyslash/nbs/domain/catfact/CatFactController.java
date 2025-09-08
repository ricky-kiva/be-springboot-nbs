package com.rickyslash.nbs.domain.catfact;

import com.rickyslash.nbs.domain.catfact.services.GetCatFactSvc;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatFactController {
  private final GetCatFactSvc getCatFactSvc;

  public CatFactController(GetCatFactSvc getCatFactSvc) {
    this.getCatFactSvc = getCatFactSvc;
  }

  @GetMapping("/catfact")
  public ResponseEntity<CatFactDTO> getCatFact(@RequestParam(defaultValue = "140") Integer maxLength) {
    return getCatFactSvc.execute(maxLength);
  }
}
