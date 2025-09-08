package com.rickyslash.nbs.domain.customer.services;

import com.rickyslash.nbs.common.contract.Query;
import com.rickyslash.nbs.common.exceptions.ErrorMessages;
import com.rickyslash.nbs.domain.customer.CustomerRepository;
import com.rickyslash.nbs.domain.customer.model.Customer;
import com.rickyslash.nbs.domain.customer.model.CustomerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetCustomerSvc implements Query<Integer, CustomerDTO> {
  private final CustomerRepository customerRepository;

  public GetCustomerSvc(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public ResponseEntity<CustomerDTO> execute(Integer id) {
    Optional<Customer> optionalCustomer = customerRepository.findById(id);
    if (optionalCustomer.isPresent()) {
      return ResponseEntity.ok(new CustomerDTO(optionalCustomer.get()));
    }

    throw new RuntimeException(ErrorMessages.CUSTOMER_NOT_FOUND.getMessage());
  }
}
