package com.isa.Bankersi2k24.controller;

import com.isa.Bankersi2k24.models.Currencies;
import com.isa.Bankersi2k24.models.Transaction;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.isa.Bankersi2k24.services.TransactionService;

import java.math.BigInteger;
import java.util.Collections;

@Controller
@RequestMapping("/web")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value="/transactions")
    public String getAllTransactions(@RequestParam("accountId") BigInteger accountId,
                                     Model model,
                                     HttpSession session) {
        BigInteger userId = (BigInteger) session.getAttribute("userId");
        try {
            model.addAttribute("content", "transactionContent")
                    .addAttribute("userId", userId)
                    .addAttribute("outgoingTransactionList", this.transactionService.getAllOutgoingTransactionsForAccount(accountId))
                    .addAttribute("incomingTransactionList", this.transactionService.getAllIncomingTransactionsForAccount(accountId));
            return "main";
        }catch (Exception e){
            model.addAttribute("content", "transactionContent")
                    .addAttribute("transactionList", Collections.emptyList())
                    .addAttribute("userId", userId)
                    .addAttribute("errorMsg", e.getMessage());
            return "main";
        }
    }

    @PostMapping(value="/transactions/new")
    public String newTransactionForm(@RequestParam("accountId") BigInteger accountId,
                                     @RequestParam("userId") BigInteger userId,
                                     Model model){
        try{
            model.addAttribute("content", "newTransaction")
                    .addAttribute("accountId", accountId)
                    .addAttribute("userId", userId)
                    .addAttribute("transaction", this.transactionService.prepareDraftTransactionForAccount(accountId))
                    .addAttribute("accountDefaultCurrency", transactionService.getCurrencyForAccount(accountId))
                    .addAttribute("currencies", Currencies.values());
            return "main";
        }catch (Exception e){
            model.addAttribute("content", "transactionContent")
                    .addAttribute("userId", userId)
                    .addAttribute("transactionList", Collections.emptyList())
                    .addAttribute("errorMsg", e.getMessage());
            return "main";
        }
    }

    @PostMapping(value = "/transactions/createNewTransaction")
    public String createNewTransaction(@ModelAttribute @Valid Transaction transaction,
                                       @RequestParam("senderAccountId") BigInteger accountId,
                                       @RequestParam("userId") BigInteger userId,
                                       Model model,
                                       BindingResult result){
        if(result.hasErrors()) {
            model.addAttribute("content", "newTransaction")
                    .addAttribute("userId", userId)
                    .addAttribute("transactionList", Collections.emptyList())
                    .addAttribute("errorMsg", "BAN in wrong format");
            return "main";
        }

        try {
            this.transactionService.saveNewTransaction(transaction);
            model.addAttribute("content", "transactionContent")
                    .addAttribute("outgoingTransactionList", this.transactionService.getAllOutgoingTransactionsForAccount(accountId))
                    .addAttribute("userId", userId)
                    .addAttribute("incomingTransactionList", this.transactionService.getAllIncomingTransactionsForAccount(accountId));
            return "main";
        }catch (Exception e){
            model.addAttribute("content", "newTransaction")
                    .addAttribute("userId", userId)
                    .addAttribute("errorMsg", e.getMessage());
            return "main";
        }
    }
}
