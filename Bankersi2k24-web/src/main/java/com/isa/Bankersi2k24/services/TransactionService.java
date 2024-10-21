package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.models.*;
import com.isa.Bankersi2k24.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final BankAccountService bankAccountService;

    public TransactionService(BankAccountService bankAccountService, TransactionRepository transactionRepository ) {
        this.transactionRepository = transactionRepository;
        this.bankAccountService = bankAccountService;
    }

    public List<Transaction> getAllTransactions(){
        return this.transactionRepository.findAll();
    }

    public List<Transaction> getAllOutgoingTransactionsForAccount(BigInteger accountId) throws Exception {
        String bankAccountNumber = bankAccountService.getBankAccount(accountId).getBankAccountNumber();
        //                .map(Transaction::getId)

        return this.getAllTransactions()
                .stream()
                .filter(t -> Objects.equals(t.getSenderBankAccountNumber(), bankAccountNumber))
//                .map(Transaction::getId)
                .toList();
        //        return this.transactionRepository.getTransactionListForIds(outgoingTransactions);
    }

    public List<Transaction> getAllIncomingTransactionsForAccount(BigInteger accountId) throws Exception {
        String bankAccountNumber = bankAccountService.getBankAccount(accountId).getBankAccountNumber();
        return this.getAllTransactions()
                .stream()
                .filter(t -> Objects.equals(t.getDestinationBankAccountNumber(), bankAccountNumber))
//                .map(Transaction::getId)
                .toList();
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

    @Transactional
    public void saveNewTransaction(Transaction transaction) {
        this.transactionRepository.save(transaction);
    }

    public void updateTransaction(Transaction transaction) {
        try{
            //this.transactionRepository.updateTransaction(transaction);
        }catch (Exception e){
            // handle this error
        }
    }

//    public boolean triggerTransaction(Transaction transaction) throws Exception {
//        BankAccount sender = bankAccountService.getBankAccount(transaction.getSenderBankAccount().getId());
//        BankAccount recipient = bankAccountService.getBankAccount(transaction.getDestinationBankAccount().getId());
//
//        try{
//            this.verifyTransaction(sender, recipient, transaction);
//            sender.setAvailableQuota(sender.getAvailableQuota().subtract(transaction.getQuota()));
//            recipient.setAvailableQuota(recipient.getAvailableQuota().add(transaction.getQuota()));
//
//            transaction.setTransactionDate(LocalDateTime.now());
//            transaction.setComplete(true);
//            transaction.setTrackingNumber();
//            this.updateTransaction(transaction);
//
//            bankAccountService.addToTransactionList(sender, transaction);
//            bankAccountService.addToTransactionList(recipient, transaction);
//
//            return true;
//        } catch (Exception e){
//            throw new Exception(e);
//        }
//    }

    public Currencies getCurrencyForAccount(BigInteger accountId) throws Exception {
        return bankAccountService.getBankAccount(accountId).getCurrency();
    }

    public Transaction prepareDraftTransactionForAccount(BigInteger accountId) {
        Transaction transaction;
        try{
            transaction = Transaction.builder()
                    .currency(bankAccountService
                            .getBankAccount(accountId)
                            .getCurrency())
                    .senderBankAccountNumber(bankAccountService
                            .getBankAccount(accountId).toString())
                    .build();
            return transaction;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Transaction> getAllTransactionsForBankAccount(BankAccount bankAccount) {
        return this.transactionRepository.findTransactionsBySenderBankAccountNumberIs(bankAccount.getBankAccountNumber());
    }
}
