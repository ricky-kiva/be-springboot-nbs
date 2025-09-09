package com.rickyslash.nbs.domain.bankaccount;

import com.rickyslash.nbs.domain.bankaccount.model.command.TransferCmd;
import com.rickyslash.nbs.domain.bankaccount.services.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankAccountController {
  private final TransferService transferService;

  public BankAccountController(TransferService transferService) {
    this.transferService = transferService;
  }

  @PostMapping("/transfer")
  public ResponseEntity<String> transfer(@RequestBody TransferCmd transfer) {
    return transferService.execute(transfer);
  }
}
