package org.sid.ebankingbackend2.repositories;

import org.sid.ebankingbackend2.entities.AccountOperation;
import org.sid.ebankingbackend2.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {

}
