package org.sid.bankaccountservice.web;

import org.sid.bankaccountservice.dtos.BankAccountRequestDTO;
import org.sid.bankaccountservice.dtos.BankAccountResponseDTO;
import org.sid.bankaccountservice.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")  //rest api: /api/bankaccounts  et spring data rest: /bankaccounts?...
public class AccountRestController {
    private final AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService=accountService;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccountResponseDTO> showBankAccounts(){
        return accountService.showBankAccounts();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO showBankAccount(@PathVariable String id){
        return accountService.showBankAccount(id);
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO addBankAccount(@RequestBody BankAccountRequestDTO bankAccountRequestDTO){
        return accountService.addBankAccount(bankAccountRequestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO updateBankAccount(@PathVariable String id,@RequestBody BankAccountRequestDTO bankAccountRequestDTO){
        return accountService.updateBankAccount(id,bankAccountRequestDTO);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteBankAccount(@PathVariable String id){
        accountService.deleteBankAccount(id);
    }
}
