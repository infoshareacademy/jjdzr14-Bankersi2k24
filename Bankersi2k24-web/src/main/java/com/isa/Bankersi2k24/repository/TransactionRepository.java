package com.isa.Bankersi2k24.repository;

import com.isa.Bankersi2k24.DAO.FileName;
import com.isa.Bankersi2k24.DAO.Serializable;
import com.isa.Bankersi2k24.models.BankAccountNumber;
import com.isa.Bankersi2k24.models.Transaction;
import com.isa.Bankersi2k24.models.User;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransactionRepository extends Serializable<Transaction> {
    private List<Transaction> transactionList;

    public TransactionRepository(FileName fileName, Class objectType) {
        super(fileName, objectType);
        invalidateTransactionList();
    }

    public List<Transaction> getAllTransactions() {
        return this.transactionList;
    }

    public List<Transaction> getTransactionListForBankAccount(BankAccountNumber bankAccountNumber){
        return this.transactionList.stream()
                .filter(t -> t.getSenderAccountNumber() == bankAccountNumber)
                .collect(Collectors.toList());
    }

    public List<Transaction> queryTransactions(Predicate<Transaction> predicate){
        return this.transactionList.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public void addTransaction(Transaction transaction){
        this.save(transaction);
        this.invalidateTransactionList();
    }

    private void invalidateTransactionList(){
        this.transactionList = fetchAllObjects();
    }

    public void updateTransaction(Transaction transaction) throws Exception{
int index = this.transactionList.indexOf(transaction);
        if(index < 0){
            throw new Exception(String.format("Bank account of id: %d does not exist", transaction.getId()));
        }else{
            this.transactionList.set(index, transaction);
            this.saveAllObjects(this.transactionList);
        }
    }

}
