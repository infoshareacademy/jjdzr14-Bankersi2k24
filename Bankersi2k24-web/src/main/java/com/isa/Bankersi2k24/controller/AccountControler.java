package com.isa.Bankersi2k24.controller;

import com.isa.Bankersi2k24.models.BankAccountNumber;
import com.isa.Bankersi2k24.services.BankAccountService;
import com.isa.Bankersi2k24.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;
import java.util.Collections;

@Controller
public class AccountControler {

    private BankAccountService bankAccountService;
    private UserService userService;

    public AccountControler(BankAccountService bankAccountService, UserService userService) {
        this.bankAccountService = bankAccountService;
        this.userService = userService;
    }

    @GetMapping("/accounts/{userId}/account-list")
    public String getAccountForUser(@PathVariable(value = "userId") BigInteger id, Model model) {
        try {
            model.addAttribute("content", "accountContent")
                    .addAttribute("userName", this.userService.getUserName(id))
                    .addAttribute("accountList", this.bankAccountService.getBankAccountsForUser(id));
            return "main";
        }catch (Exception e){
            model.addAttribute("content", "accountContent")
                    .addAttribute("userName", "")
                    .addAttribute("accountList", Collections.emptyList())
                    .addAttribute("errorMsg", e.getMessage());
            return "main";
        }
    }
}
