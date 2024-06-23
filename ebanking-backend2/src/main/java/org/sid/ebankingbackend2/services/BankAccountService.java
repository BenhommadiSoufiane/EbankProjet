package org.sid.ebankingbackend2.services;

import org.sid.ebankingbackend2.dtos.*;
import org.sid.ebankingbackend2.entities.BankAccount;
import org.sid.ebankingbackend2.entities.CurrentAccount;
import org.sid.ebankingbackend2.entities.Customer;
import org.sid.ebankingbackend2.entities.SavingAccount;
import org.sid.ebankingbackend2.exceptions.BalanceNotsufficentException;
import org.sid.ebankingbackend2.exceptions.BankAccountNotFoundException;
import org.sid.ebankingbackend2.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
   CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId,double amount,String description) throws BankAccountNotFoundException, BalanceNotsufficentException;
    void credit(String accountId,double amount,String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource,String accountIdDestination,double amount) throws BankAccountNotFoundException, BalanceNotsufficentException;

    List<BankAccountDTO> bankAccountList();

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

 void deleteCustomer(Long customerId);

 List<AccountOperationDTO> accountHistory(String accountId);

 AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;

    List<CustomerDTO> searchCustomers(String keyword);
}
