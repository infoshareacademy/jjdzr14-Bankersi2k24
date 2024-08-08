package com.isa.Bankersi2k24.controller;

import com.isa.Bankersi2k24.models.Currencies;
import com.isa.Bankersi2k24.models.Transaction;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.isa.Bankersi2k24.services.TransacrionService;

import java.math.BigInteger;
import java.util.Collections;

@Controller
public class TransactionController {
    private final TransacrionService transacrionService;

    public TransactionController(TransacrionService transacrionService) {
        this.transacrionService = transacrionService;
    }

    @PostMapping(value="/transactions")
    public String getAllTransactions(@RequestParam("accountId") BigInteger accountId, Model model){
        try {
            model.addAttribute("content", "transactionContent")
                    .addAttribute("outgoingTransactionList", this.transacrionService.getAllOutgoingTransactionsForAccount(accountId))
                    .addAttribute("incomingTransactionList", this.transacrionService.getAllIncommingTransactionsForAccount(accountId));
            return "main";
        }catch (Exception e){
            model.addAttribute("content", "transactionContent")
                    .addAttribute("transactionList", Collections.emptyList())
                    .addAttribute("errorMsg", e.getMessage());
            return "main";
        }
    }

    @PostMapping(value="/transactions/new")
    public String newTransactionForm(@RequestParam("accountId") BigInteger accountId, Model model){
        try{
            model.addAttribute("content", "newTransaction")
                    .addAttribute("accountId", accountId)
                    .addAttribute("transaction", this.transacrionService.prepareDraftTransactionForAccount(accountId))
                    .addAttribute("accountDefaultCurrency",transacrionService.getCurrencyForAccount(accountId))
                    .addAttribute("currencies", Currencies.values());
            return "main";
        }catch (Exception e){
            model.addAttribute("content", "transactionContent")
                    .addAttribute("transactionList", Collections.emptyList())
                    .addAttribute("errorMsg", e.getMessage());
            return "main";
        }
    }

    @PostMapping(value = "/transactions/createNewTransaction")
    public String createNewTransaction(@ModelAttribute @Valid Transaction transaction, Model model, BindingResult result){
        if(result.hasErrors()) {
            model.addAttribute("content", "newTransaction")
                    .addAttribute("transactionList", Collections.emptyList())
                    .addAttribute("errorMsg", "BAN in wrong format");
        }
        return "main";
    }
}
