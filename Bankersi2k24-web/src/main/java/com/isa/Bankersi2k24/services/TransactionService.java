package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.models.*;
import com.isa.Bankersi2k24.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final BankAccountService bankAccountService;

    public TransactionService(BankAccountService bankAccountService, TransactionRepository transactionRepository ) {
        this.transactionRepository = transactionRepository;
        this.bankAccountService = bankAccountService;
    }

    public List<Transaction> getAllTransactions(){
        return null;
//        return this.transactionRepository.getAllTransactions();
    }

    public List<Transaction> getAllOutgoingTransactionsForAccount(BigInteger accountId) throws Exception {
//        BankAccountNumber bankAccountNumber = bankAccountService.getBankAccount(accountId).getBankAccountNumber();
//        List<BigInteger> outgoingTransactions = this.getAllTransactions()
//                .stream()
//                .filter(t -> t.getSenderBankAccount().getBankAccountNumber() == bankAccountNumber)
//                .map(Transaction::getId)
//                .toList();
        return null;
        //        return this.transactionRepository.getTransactionListForIds(outgoingTransactions);
    }

    public List<Transaction> getAllIncomingTransactionsForAccount(BigInteger accountId) throws Exception {
//        BankAccountNumber bankAccountNumber = bankAccountService.getBankAccount(accountId).getBankAccountNumber();
//        List<BigInteger> incomingTransactions = this.getAllTransactions()
//                .stream()
//                .filter(t -> t.getDestinationBankAccount().getBankAccountNumber() == bankAccountNumber)
//                .map(Transaction::getId)
//                .toList();
        return null;
//        return this.transactionRepository.getTransactionListForIds(incomingTransactions);
    }

    private boolean verifyTransaction(BankAccount sender, BankAccount recepient, Transaction transaction) throws RuntimeException{
        if(sender.getAvailableQuota().compareTo(transaction.getQuota()) < 0 ){
            throw new RuntimeException("Not enough quota on sender account");
        }
        if(transaction.getCurrency() != recepient.getCurrency() || transaction.getCurrency() != sender.getCurrency()){
            throw new RuntimeException("Currencies do not match");
        }
        return true;
    }

    public void saveNewTransaction(Transaction transaction) throws Exception {
        //this.transactionRepository.addTransaction(transaction);

        BankAccount sender = bankAccountService.getBankAccount(transaction.getSenderBankAccount().getId());
        BankAccount recipient = bankAccountService.getBankAccount(transaction.getDestinationBankAccount().getId());

        this.verifyTransaction(sender, recipient, transaction);
        bankAccountService.addToTransactionList(sender, transaction);
        bankAccountService.addToTransactionList(recipient, transaction);


    }

    public void updateTransaction(Transaction transaction) {
        try{
            //this.transactionRepository.updateTransaction(transaction);
        }catch (Exception e){
            // handle this error
        }
    }

    public boolean triggerTransaction(Transaction transaction) throws Exception {
        BankAccount sender = bankAccountService.getBankAccount(transaction.getSenderBankAccount().getId());
        BankAccount recipient = bankAccountService.getBankAccount(transaction.getDestinationBankAccount().getId());

        try{
            this.verifyTransaction(sender, recipient, transaction);
            sender.setAvailableQuota(sender.getAvailableQuota().subtract(transaction.getQuota()));
            recipient.setAvailableQuota(recipient.getAvailableQuota().add(transaction.getQuota()));

            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setComplete(true);
            transaction.setTrackingNumber();
            this.updateTransaction(transaction);

            bankAccountService.addToTransactionList(sender, transaction);
            bankAccountService.addToTransactionList(recipient, transaction);

            return true;
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    public Currencies getCurrencyForAccount(BigInteger accountId) throws Exception {
        return bankAccountService.getBankAccount(accountId).getCurrency();
    }

    public Transaction prepareDraftTransactionForAccount(BigInteger accountId) {
        Transaction transaction = new Transaction();
        try{
            transaction.setCurrency(bankAccountService
                    .getBankAccount(accountId)
                    .getCurrency());
            transaction.setSenderBankAccount(bankAccountService
                    .getBankAccount(accountId));
            return transaction;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
