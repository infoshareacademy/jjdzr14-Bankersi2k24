package com.isa.Bankersi2k24.controller;

import com.isa.Bankersi2k24.models.Dashboard;
import com.isa.Bankersi2k24.services.BankAccountNumberService;
import com.isa.Bankersi2k24.services.BankAccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/web")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/bankAccount/{bankAccountId}")
    public String findBankAccountById(@PathVariable("bankAccountId") String bankAccountId, Model model, HttpSession session) {
        System.out.println("username = " + session.getAttribute("username"));
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

    @GetMapping("/test")
    public String test(Model model) {
        return "test";
    }
}
