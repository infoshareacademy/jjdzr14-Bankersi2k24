package com.isa.Bankersi2k24.controller;

import com.isa.Bankersi2k24.models.Dashboard;
import com.isa.Bankersi2k24.services.BankAccountNumberService;
import com.isa.Bankersi2k24.services.BankAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/bankAccount/{bankAccountId}")
    public String findBankAccountById(@PathVariable("bankAccountId") String bankAccountId, Model model) {
        System.out.println("bankAccountId = " + bankAccountId);
        String bankAccountNumber = "brak";
        try {
            bankAccountNumber = BankAccountNumberService.accountNumberStringToBan(bankAccountId).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("bankAccountNumber", bankAccountNumber);
        model.addAttribute("bankAccountId", bankAccountId);
        System.out.println(bankAccountNumber);
        return "bankAccountHtml";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, @ModelAttribute("userId") BigInteger userId, BindingResult bindingResult) {
        List<String> errorMsg = new ArrayList<>();
        errorMsg = Arrays.asList(bindingResult.getSuppressedFields());


        Dashboard dashboard = this.bankAccountService.prepareUserDashboard(userId);
        model.addAttribute("content", "dashboard")
                .addAttribute("dashboard", dashboard);
        return "main";
    }
}
