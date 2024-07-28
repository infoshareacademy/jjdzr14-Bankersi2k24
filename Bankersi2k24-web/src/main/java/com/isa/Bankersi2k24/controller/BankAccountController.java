package com.isa.Bankersi2k24.controller;

import com.isa.Bankersi2k24.services.BankAccountNumberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BankAccountController {
    @GetMapping("/bankAccount/{bankAccountId}")
    public String findBankAccountById(@PathVariable("bankAccountId")String bankAccountId, Model model){
        System.out.println("bankAccountId = "+bankAccountId);
        String bankAccountNumber = "brak";
        try {
            bankAccountNumber = BankAccountNumberService.accountNumberStringToBan(bankAccountId).toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("bankAccountNumber", bankAccountNumber);
        model.addAttribute("bankAccountId", bankAccountId);
        System.out.println(bankAccountNumber);
        return "bankAccountHtml";
    }
}
