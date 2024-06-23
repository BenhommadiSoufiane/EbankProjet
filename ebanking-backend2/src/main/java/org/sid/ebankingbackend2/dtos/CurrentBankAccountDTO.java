 package org.sid.ebankingbackend2.dtos;

import lombok.Data;
import org.sid.ebankingbackend2.enums.AccountStatus;

import java.util.Date;

@Data
public  class CurrentBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date CreatedAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double overDraft;

}
