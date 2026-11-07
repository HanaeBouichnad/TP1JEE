package org.sid.bankaccountservice;

import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.enums.AccountType;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class BankAccountServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(BankAccountServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountrepository) {
        return args -> {
            BankAccount A1 = BankAccount.builder()
                    .id(UUID.randomUUID().toString())
                    .createdAt(new Date())
                    .balance(10000.0)
                    .currency("MAD")
                    .type(AccountType.CURRENT_ACCOUNT)
                    .build();

            BankAccount A2 = BankAccount.builder()
                    .id(UUID.randomUUID().toString())
                    .createdAt(new Date())
                    .balance(20000.0)
                    .currency("MAD")
                    .type(AccountType.SAVING_ACCOUNT)
                    .build();

            BankAccount A3 = BankAccount.builder()
                    .id(UUID.randomUUID().toString())
                    .createdAt(new Date())
                    .balance(30000.0)
                    .currency("MAD")
                    .type(AccountType.CURRENT_ACCOUNT)
                    .build();

            bankAccountrepository.save(A1);
            bankAccountrepository.save(A2);
            bankAccountrepository.save(A3);

        };
    }
}
