package org.sid.bankaccountservice.services;

import org.sid.bankaccountservice.dtos.BankAccountRequestDTO;
import org.sid.bankaccountservice.dtos.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.mappers.AccountMapper;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service @Transactional
public class AccountServiceImpl implements AccountService {
    private final BankAccountRepository bankAccountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(BankAccountRepository bankAccountRepository,AccountMapper accountMapper){
        this.bankAccountRepository=bankAccountRepository;
        this.accountMapper=accountMapper;
    }

    @Override
    public List<BankAccountResponseDTO> showBankAccounts(){
        List<BankAccount> bankAccounts=bankAccountRepository.findAll();
        return bankAccounts.stream()
                .map(accountMapper::toBankAccountResponseDTO)
                .toList();
    }

    @Override
    public BankAccountResponseDTO showBankAccount(String id){
        BankAccount bankAccount=bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException("Bank Account not found!"));
        return accountMapper.toBankAccountResponseDTO(bankAccount);
    }

    @Override
    public BankAccountResponseDTO addBankAccount(BankAccountRequestDTO bankAccountRequestDTO){
        BankAccount bankAccount=BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountRequestDTO.getBalance())
                .currency(bankAccountRequestDTO.getCurrency())
                .type(bankAccountRequestDTO.getType())
                .build();
        BankAccount bankAccountAdded=bankAccountRepository.save(bankAccount);
        return accountMapper.toBankAccountResponseDTO(bankAccountAdded);
    }

    @Override
    public BankAccountResponseDTO updateBankAccount(String id,BankAccountRequestDTO bankAccountRequestDTO){
        BankAccount bankAccount=bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException("Bank Account not found!"));
        if(bankAccountRequestDTO.getBalance()!=null) bankAccount.setBalance(bankAccountRequestDTO.getBalance());
        if(bankAccountRequestDTO.getCurrency()!=null) bankAccount.setCurrency(bankAccountRequestDTO.getCurrency());
        if(bankAccountRequestDTO.getType()!=null) bankAccount.setType(bankAccountRequestDTO.getType());
        BankAccount bankAccountUpdated=bankAccountRepository.save(bankAccount);
        return accountMapper.toBankAccountResponseDTO(bankAccountUpdated);
    }

    @Override
    public void deleteBankAccount(String id){
        BankAccount bankAccount=bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException("Bank Account not found!"));
        bankAccountRepository.delete(bankAccount);
    }

}
