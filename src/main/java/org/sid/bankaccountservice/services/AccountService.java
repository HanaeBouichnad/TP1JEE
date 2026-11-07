package org.sid.bankaccountservice.services;

import org.sid.bankaccountservice.dtos.BankAccountRequestDTO;
import org.sid.bankaccountservice.dtos.BankAccountResponseDTO;

import java.util.List;

public interface AccountService {
    List<BankAccountResponseDTO> showBankAccounts();
    BankAccountResponseDTO showBankAccount(String id);
    BankAccountResponseDTO addBankAccount(BankAccountRequestDTO bankAccountRequestDTO);
    BankAccountResponseDTO updateBankAccount(String id,BankAccountRequestDTO bankAccountRequestDTO);
    void deleteBankAccount(String id);
}
