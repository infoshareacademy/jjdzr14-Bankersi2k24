package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.DAO.FileName;
import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.models.Currencies;
import com.isa.Bankersi2k24.models.Transaction;
import com.isa.Bankersi2k24.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Currency;
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

    public List<Transaction> getAllOutgoingTransactionsForAccount(BigInteger accountId){
        BankAccountService bankAccountService = new BankAccountService();
        try {
            List<BigInteger> transactions = bankAccountService
                    .getBankAccount(accountId)
                    .getOutGoingTransactionList();
            return this.transactionRepository.getTransactionListForIds(transactions);
        }catch (Exception e){
            throw new RuntimeException(String.format("No transactions were found for account: %d", accountId));
        }
    }

    public List<Transaction> getAllIncommingTransactionsForAccount(BigInteger accountId){
        BankAccountService bankAccountService = new BankAccountService();
        try {
            List<BigInteger> transactions = bankAccountService
                    .getBankAccount(accountId)
                    .getIncomingTransactionList();
            return this.transactionRepository.getTransactionListForIds(transactions);
        }catch (Exception e){
            throw new RuntimeException(String.format("No transactions were found for account: %d", accountId));
        }
    }

    private boolean verifyTransaction(BankAccount sender, BankAccount recepient, Transaction transaction) throws RuntimeException{
        if(sender.getAvailableQuota() < transaction.getQuota()){
            throw new RuntimeException("Not enough quota on sender account");
        }
        if(sender.getCurrency() != recepient.getCurrency()){
            throw new RuntimeException("Currencies do not match");
        }
        return true;
    }

    public void saveNewTransaction(Transaction transaction){
        this.transactionRepository.addTransaction(transaction);

        BankAccountService bankAccountService = new BankAccountService();
        BankAccount sender = bankAccountService.getBankAccount(transaction.getSenderAccountNumber());
        BankAccount recipient = bankAccountService.getBankAccount(transaction.getDestinationAccountNumber());

        bankAccountService.addToTransactionList(sender, transaction);
        bankAccountService.addToTransactionList(recipient, transaction);
    }

    public void updateTransaction(Transaction transaction) {
        try{
            this.transactionRepository.updateTransaction(transaction);
        }catch (Exception e){
            // handle this error
        }
    }

    public boolean triggerTransaction(Transaction transaction){
        BankAccountService bankAccountService = new BankAccountService();
        BankAccount sender = bankAccountService.getBankAccount(transaction.getSenderAccountNumber());
        BankAccount recipient = bankAccountService.getBankAccount(transaction.getDestinationAccountNumber());

        try{
            this.verifyTransaction(sender, recipient, transaction);
            sender.setAvailableQuota(sender.getAvailableQuota()-transaction.getQuota());
            recipient.setAvailableQuota(recipient.getAvailableQuota()+transaction.getQuota());

            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setComplete(true);
            transaction.setTrackingNumber();
            this.updateTransaction(transaction);

            bankAccountService.addToTransactionList(sender, transaction);
            bankAccountService.addToTransactionList(recipient, transaction);

            return true;
        } catch (Exception e){
            //there was no available quota, print the msg
        }
        return false;
    }

    public Currencies getCurrencyForAccount(BigInteger accountId) throws Exception {
        BankAccountService bankAccountService = new BankAccountService();
        return bankAccountService.getBankAccount(accountId).getCurrency();
    }

    public Transaction prepareDraftTransactionForAccount(BigInteger accountId) {
        BankAccountService bankAccountService = new BankAccountService();
        Transaction transaction = new Transaction();
        try{
            transaction.setCurrency(bankAccountService
                    .getBankAccount(accountId)
                    .getCurrency());
            transaction.setSenderAccountNumber(bankAccountService
                    .getBankAccount(accountId)
                    .getBankAccountNumber());
            return transaction;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
