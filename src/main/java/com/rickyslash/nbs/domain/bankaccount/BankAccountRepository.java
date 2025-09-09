package com.rickyslash.nbs.domain.bankaccount;

import com.rickyslash.nbs.domain.bankaccount.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
