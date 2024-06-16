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

    public Transaction(String tranasactionTitle, int quota, BankAccountNumber senderAccountNumber, BankAccountNumber destinationAccountNumber) {
        super(FileName.TRANSACITON, Transaction.class);
        this.transactionTitle = tranasactionTitle;
        this.quota = quota;
        this.senderAccountNumber = senderAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public String getTranasactionTitle() {
        return transactionTitle;
    }

    public void setTranasactionTitle(String tranasactionTitle) {
        this.transactionTitle = tranasactionTitle;
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