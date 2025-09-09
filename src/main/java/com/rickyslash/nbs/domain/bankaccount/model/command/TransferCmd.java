package com.rickyslash.nbs.domain.bankaccount.model.command;

import lombok.Data;

@Data
public class TransferCmd {
  private String fromAccount;
  private String toAccount;
  private double amount;
}
