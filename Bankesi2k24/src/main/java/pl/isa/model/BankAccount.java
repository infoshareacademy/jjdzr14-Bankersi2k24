package pl.isa.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class BankAccount {
    private Integer accountNumber;
    private Integer availableQuota;
    private Currencies currency;
    private List<Transaction> transactionList;

    public BankAccount() {
        /**
         * @method used to create a new (non existing) bank account, thus no params needed
         */

    }

    public BankAccount(Integer accountNumber) {
        /**
         * finds a bank account in DB bu it's number
         * @param the bank accounts number
         */

    }

    public BankAccount createFakeBankAccount(Integer accountNumber, Integer availableQuota, Currencies currency) {
        this.accountNumber = accountNumber;
        this.availableQuota = availableQuota;
        this.currency = currency;
        return this;
    }

    private boolean generateAccountNumber(){
        Random rnd = new Random();
        this.accountNumber = rnd.nextInt();
        return true;
    }

    public Currencies getCurrency() {
        return currency;
    }

    public Integer getAccountNumber() {
        return this.accountNumber;
    }

    public Integer getAvailableQuota() {
        return this.availableQuota;
    }

    public void setAvailableQuota(Integer availableQuota) {
        this.availableQuota = availableQuota;
    }
    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public boolean addToTransactionList(Transaction transaction) {
        if(this.transactionList != null){
            this.transactionList.add(transaction);
            return true;
        }else{
            //try reading from DB again? and then  save
            return false;
        }

    }
}
