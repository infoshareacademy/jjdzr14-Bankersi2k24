package com.isa.Bankersi2k24.controller;

import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.repository.BankAccountRepository;
import com.isa.Bankersi2k24.services.BankAccountNumberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.function.Predicate;

@Controller
public class BankAccountController {
    @GetMapping("/bankAccount/{bankAccountId}")
    public String findBankAccountById(@PathVariable("bankAccountId")Integer id, Model model){
        BankAccount bankAccount = null;
        BankAccountRepository bankAccountRepository = BankAccountRepository.BankAccountRepository();
        Predicate<BankAccount> predicate = (BankAccount bankAccount1)->bankAccount1.getUserId()== id;
        if (bankAccountRepository.queryBankAccounts(predicate)){
            bankAccount = bankAccountRepository.bankAccountByUserID(predicate);

        }
        if(bankAccount != null){
            model.addAttribute("bankAccountNumber", bankAccount.getBankAccountNumber().toString());
            model.addAttribute("availableQuota", bankAccount.getAvailableQuota());
            model.addAttribute("currency", bankAccount.getCurrency().toString());
        } else{
            model.addAttribute("bankAccountNumber", " - ");
            model.addAttribute("availableQuota", " - ");
            model.addAttribute("currency", " - ");

        }
        model.addAttribute("bankAccountId", id);
        return "bankAccountHtml";
    }
}
