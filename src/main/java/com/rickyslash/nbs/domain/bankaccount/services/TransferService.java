package com.rickyslash.nbs.domain.bankaccount.services;

import com.rickyslash.nbs.common.contract.Command;
import com.rickyslash.nbs.domain.bankaccount.BankAccountRepository;
import com.rickyslash.nbs.domain.bankaccount.model.BankAccount;
import com.rickyslash.nbs.domain.bankaccount.model.command.TransferCmd;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransferService implements Command<TransferCmd, String> {
  private final BankAccountRepository bankAccountRepository;

  public TransferService(BankAccountRepository bankAccountRepository) {
    this.bankAccountRepository = bankAccountRepository;
  }

  @Override
  @Transactional
  public ResponseEntity<String> execute(TransferCmd transfer) {
    Optional<BankAccount> optionalFromAccount = bankAccountRepository.findById(transfer.getFromAccount());
    Optional<BankAccount> optionalToAccount = bankAccountRepository.findById(transfer.getToAccount());

    if (optionalFromAccount.isEmpty() || optionalToAccount.isEmpty()) {
      throw new RuntimeException("User not found");
    }

    BankAccount fromAccount = optionalFromAccount.get();
    BankAccount toAccount = optionalToAccount.get();

    add(toAccount, transfer.getAmount());
    deduct(fromAccount, transfer.getAmount());

    return ResponseEntity.ok("Transfer success");
  }

  private void deduct(BankAccount bankAccount, double amount) {
    if (bankAccount.getBalance() < amount) {
      throw new RuntimeException("Not enough balance");
    }

    bankAccount.setBalance(bankAccount.getBalance() - amount);
  }

  private void add(BankAccount bankAccount, double amount) {
    bankAccount.setBalance(bankAccount.getBalance() + amount);
  }
}
