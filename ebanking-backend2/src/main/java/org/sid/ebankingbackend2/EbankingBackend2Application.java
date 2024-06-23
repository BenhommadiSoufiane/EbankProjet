package org.sid.ebankingbackend2;

import org.sid.ebankingbackend2.dtos.BankAccountDTO;
import org.sid.ebankingbackend2.dtos.CurrentBankAccountDTO;
import org.sid.ebankingbackend2.dtos.CustomerDTO;
import org.sid.ebankingbackend2.dtos.SavingBankAccountDTO;
import org.sid.ebankingbackend2.entities.*;
import org.sid.ebankingbackend2.enums.AccountStatus;
import org.sid.ebankingbackend2.enums.OperationType;
import org.sid.ebankingbackend2.exceptions.BalanceNotsufficentException;
import org.sid.ebankingbackend2.exceptions.BankAccountNotFoundException;
import org.sid.ebankingbackend2.exceptions.CustomerNotFoundException;
import org.sid.ebankingbackend2.repositories.AccountOperationRepository;
import org.sid.ebankingbackend2.repositories.BankAccountRepository;
import org.sid.ebankingbackend2.repositories.CustomerRepository;
import org.sid.ebankingbackend2.services.BankAccountService;
import org.sid.ebankingbackend2.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackend2Application {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackend2Application.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
        return args -> {
            Stream.of("Soufiane","Hmida","danilo").forEach(name->{
                CustomerDTO customer=new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCustomers().forEach(customer->{
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000, customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5, customer.getId());
                    List<BankAccountDTO> bankAccounts=bankAccountService.bankAccountList();
                    for (BankAccountDTO bankAccount:bankAccounts) {
                        for (int i = 0; i < 10; i++) {
                            String accountId;
                            if (bankAccount instanceof SavingBankAccountDTO){
                                accountId=((SavingBankAccountDTO) bankAccount).getId();
                            }else {
                                accountId=((CurrentBankAccountDTO) bankAccount).getId();
                            }
                            bankAccountService.credit(accountId, 10000 + Math.random() * 12000, "Credit");
                            bankAccountService.debit(accountId,1000+Math.random()*9000,"Debit");


                    }
                }
            } catch (CustomerNotFoundException | BalanceNotsufficentException e) {e.printStackTrace();}
                catch (BankAccountNotFoundException e) {e.printStackTrace();};
        });
    };
}
    //@Bean

CommandLineRunner start(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository, AccountOperationRepository accountOperationRepository){
        return args -> {
            Stream.of("Hassan","Yassine","Aicha").forEach(name->{
                Customer customer=new Customer();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                customerRepository.save(customer);

            });
            customerRepository.findAll().forEach(cust->{
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*90000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverdraft(9000);
                bankAccountRepository.save(currentAccount);


                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random()*90000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);

            });
            bankAccountRepository.findAll().forEach(acc-> {
                for (int i = 0; i < 5; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random() * 12000);
                    accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);
                }
            });

        };
}
}
