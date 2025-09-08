package com.rickyslash.nbs.domain.customer;

import com.rickyslash.nbs.domain.customer.model.CustomerDTO;
import com.rickyslash.nbs.domain.customer.services.GetCustomerSvc;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
  private final GetCustomerSvc getCustomerSvc;

  public CustomerController(GetCustomerSvc getCustomerSvc) {
    this.getCustomerSvc = getCustomerSvc;
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id) {
    return getCustomerSvc.execute(id);
  }
}
