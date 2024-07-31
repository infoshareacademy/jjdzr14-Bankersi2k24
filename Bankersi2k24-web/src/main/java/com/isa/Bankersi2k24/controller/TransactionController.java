package com.isa.Bankersi2k24.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.isa.Bankersi2k24.services.TransacrionService;

@Controller
public class TransactionController {
    private final TransacrionService transacrionService;

    public TransactionController(TransacrionService transacrionService) {
        this.transacrionService = transacrionService;
    }

    @GetMapping("/transactions")
    public String getAllTransactions(Model model){
        model.addAttribute("content", "transactionContent")
                .addAttribute("transactionList", this.transacrionService.getAllTransactions());
        return "main";
    }

}
