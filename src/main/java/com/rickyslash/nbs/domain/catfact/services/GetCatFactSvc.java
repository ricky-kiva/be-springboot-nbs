package com.rickyslash.nbs.domain.catfact.services;

import com.rickyslash.nbs.common.contract.Query;
import com.rickyslash.nbs.domain.catfact.CatFact;
import com.rickyslash.nbs.domain.catfact.CatFactDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@Service
public class GetCatFactSvc implements Query<Integer, CatFactDTO> {
  private final RestTemplate restTemplate;

  public GetCatFactSvc(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public ResponseEntity<CatFactDTO> execute(Integer input) {
    final String BASE_URI = "https://catfact.ninja/fact";
    final String MAX_LENGTH = "max_length";
    final String APPLICATION_JSON = "application/json";

    URI uri = UriComponentsBuilder
        .fromUriString(BASE_URI)
        .queryParam(MAX_LENGTH, input)
        .build()
        .toUri();

    HttpHeaders headers = new HttpHeaders();

    headers.set(HttpHeaders.ACCEPT, APPLICATION_JSON);

    HttpEntity<String> entity = new HttpEntity<>(headers);

    try {
      ResponseEntity<CatFact> res = restTemplate
          .exchange(uri, HttpMethod.GET, entity, CatFact.class);

      CatFactDTO catFactDTO = new CatFactDTO(Objects.requireNonNull(res.getBody()));

      return ResponseEntity.ok()
          .contentType(MediaType.APPLICATION_JSON)
          .body(catFactDTO);
    } catch (Exception e) {
      throw new RuntimeException("Cat Facts API is down");
    }
  }
}
