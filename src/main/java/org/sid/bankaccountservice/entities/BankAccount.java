package org.sid.bankaccountservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bankaccountservice.enums.AccountType;

import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String id;

    private Date createdAt;

    private Double balance;  //valeurs par defaut: si double:0 et si Double:null

    private String currency;

    @Enumerated(EnumType.STRING)
    private AccountType type;
}
