package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.DAO.FileName;
import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.models.Transaction;
import com.isa.Bankersi2k24.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransacrionService {

    private TransactionRepository transactionRepository;

    public TransacrionService() {
        this.transactionRepository = new TransactionRepository(FileName.TRANSACITON, Transaction.class);
    }

    public List<Transaction> getAllTransactions(){
        return this.transactionRepository.getAllTransactions();
    }

    private boolean verifyTransaction(BankAccount sender, BankAccount recepient) throws RuntimeException{
        if(sender.getAvailableQuota() < recepient.getAvailableQuota()){
            throw new RuntimeException("Not enough quota on sender account");
        }
        return true;
    }

    public void saveNewTransaction(Transaction transaction){
        this.transactionRepository.addTransaction(transaction);
    }

    public boolean triggerTransaction(Transaction transaction){
        BankAccountService bankAccountService = new BankAccountService();
        BankAccount sender = bankAccountService.getBankAccount(transaction.getSenderAccountNumber());
        BankAccount recipient = bankAccountService.getBankAccount(transaction.getDestinationAccountNumber());

        if(this.verifyTransaction(sender, recipient)){
            sender.setAvailableQuota(sender.getAvailableQuota()-transaction.getQuota());
            recipient.setAvailableQuota(recipient.getAvailableQuota()+transaction.getQuota());

            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setComplete(true);
            transaction.setTrackingNumber();

            bankAccountService.addToTransactionList(sender, transaction);
            bankAccountService.addToTransactionList(recipient, transaction);

            return true;
        }
        return false;
    }
}
