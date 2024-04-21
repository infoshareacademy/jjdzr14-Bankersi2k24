package pl.isa.service;

import pl.isa.model.BankAccount;
import pl.isa.model.Transaction;

public class TransactionService {
    public BankAccount recalculateSaldo(BankAccount bankAccount, Transaction transaction){
        double currentBalance = bankAccount.getCurrentBalance();
        double transactionAmount = transaction.getTransactionAmount();
        currentBalance = 10000;
        transactionAmount = 100;

                return bankAccount;
    }

}
