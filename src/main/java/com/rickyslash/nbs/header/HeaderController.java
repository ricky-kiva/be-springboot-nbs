package com.rickyslash.nbs.header;

import com.rickyslash.nbs.header.services.GetProductWithHeaderSvc;
import com.rickyslash.nbs.header.services.GetRegionHeaderResponseSvc;
import com.rickyslash.nbs.product.model.ProductDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController {
  private final GetRegionHeaderResponseSvc getRegionHeaderResponseSvc;
  private final GetProductWithHeaderSvc getProductWithHeaderSvc;

  public HeaderController(
      GetRegionHeaderResponseSvc getRegionHeaderResponseSvc,
      GetProductWithHeaderSvc getProductWithHeaderSvc
  ) {
    this.getRegionHeaderResponseSvc = getRegionHeaderResponseSvc;
    this.getProductWithHeaderSvc = getProductWithHeaderSvc;
  }

  @GetMapping("/header")
  public ResponseEntity<String> getRegionalResponse(
      @RequestHeader(required = false, defaultValue = "ID") String region
  ) {
    return getRegionHeaderResponseSvc.execute(region);
  }

  @GetMapping(
      value = "/header/product",
      produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
  public ResponseEntity<ProductDTO> getProductWithHeaderResponse() {
    return getProductWithHeaderSvc.execute(null);
  }
}
