package com.isa.Bankersi2k24.models;

import com.isa.Bankersi2k24.DAO.FileName;
import com.isa.Bankersi2k24.DAO.Serializable;

import java.util.Date;

public class Transaction extends Serializable<Transaction> {
    private String transactionTitle;
    private int quota;
    private BankAccountNumber senderAccountNumber;
    private BankAccountNumber destinationAccountNumber;
    private Date transactionDate;
    private boolean isComplete;

    public Transaction(String tranasactionTitle, int quota, BankAccountNumber senderAccountNumber, BankAccountNumber destinationAccountNumber) {
        super(FileName.TRANSACITON, Transaction.class);
        this.transactionTitle = tranasactionTitle;
        this.quota = quota;
        this.senderAccountNumber = senderAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;
        this.isComplete = false;
    }

    public String getTransactionTitle() {
        return transactionTitle;
    }

    public void setTransactionTitle(String transactionTitle) {
        this.transactionTitle = transactionTitle;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public BankAccountNumber getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(BankAccountNumber senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public BankAccountNumber getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(BankAccountNumber destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }



}
