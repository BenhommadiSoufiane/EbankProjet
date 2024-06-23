package org.sid.ebankingbackend2.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.ebankingbackend2.entities.BankAccount;

import java.util.List;

@Data
public class CustomerDTO {
    private Long id;
    private  String name;
    private String email;

}
