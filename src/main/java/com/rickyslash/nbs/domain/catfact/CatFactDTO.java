package com.rickyslash.nbs.domain.catfact;

import lombok.Data;

@Data
public class CatFactDTO {
  private String fact;

  public CatFactDTO(CatFact catFact) {
    this.fact = catFact.getFact();
  }
}
