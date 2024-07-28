package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.models.Transaction;

import java.time.LocalDateTime;
import java.util.Date;

public class TransacrionService {
    private boolean verifyTransaction(BankAccount sender, BankAccount recepient) throws RuntimeException{
        if(sender.getAvailableQuota() < recepient.getAvailableQuota()){
            throw new RuntimeException("Not enough quota on sender account");
        }
        return true;
    }

    public boolean triggerTransaction(Transaction transaction){
        BankAccountService bankAccountService = new BankAccountService();
        BankAccount sender = bankAccountService.getBankAccount(transaction.getSenderAccountNumber());
        BankAccount recipient = bankAccountService.getBankAccount(transaction.getDestinationAccountNumber());

        if(this.verifyTransaction(sender, recipient)){
            sender.setAvailableQuota(sender.getAvailableQuota()-transaction.getQuota());
            recipient.setAvailableQuota(recipient.getAvailableQuota()+transaction.getQuota());
            transaction.setTransactionDate(LocalDateTime.now());
            if(BankAccountService.addToTransactionList(sender, transaction) &&
                    BankAccountService.addToTransactionList(recipient, transaction)){
                transaction.setComplete(true);
                return true;
            }
        }
        return false;
    }
}
