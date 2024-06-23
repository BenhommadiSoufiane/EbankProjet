package org.sid.ebankingbackend2.dtos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.ebankingbackend2.entities.BankAccount;
import org.sid.ebankingbackend2.enums.OperationType;

import java.util.Date;

@Data
public class AccountOperationDTO {
    private  long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;

}
