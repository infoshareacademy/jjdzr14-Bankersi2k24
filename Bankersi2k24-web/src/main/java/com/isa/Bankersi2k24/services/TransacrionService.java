package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.models.Transaction;

import java.util.Date;

public class TransacrionService {
    private boolean verifyTransaction(BankAccount sender, BankAccount recepient) throws RuntimeException{
        if(sender.getAvailableQuota() < recepient.getAvailableQuota()){
            throw new RuntimeException("Not enough quota on sender account");
        }
        return true;
    }

    public boolean triggerTransaction(Transaction transaction){
        BankAccount sender = new BankAccount(transaction.getSenderAccountNumber());
        BankAccount recipient = new BankAccount(transaction.getDestinationAccountNumber());
        if(this.verifyTransaction(sender, recipient)){
            sender.setAvailableQuota(sender.getAvailableQuota()-transaction.getQuota());
            recipient.setAvailableQuota(recipient.getAvailableQuota()+transaction.getQuota());
            transaction.setTransactionDate(new Date());
            sender.addToTransactionList(transaction);
            recipient.addToTransactionList(transaction);
            return true;
        }
        return false;
    }
}
