package com.isa.Bankersi2k24.models;

import com.isa.Bankersi2k24.dataAccess.FileName;
import com.isa.Bankersi2k24.dataAccess.Serializable;

import java.util.Date;

public class Transaction extends Serializable<Transaction> {
    private String transactionTitle;
    private int quota;
    private int senderAccountNumber;
    private int destinationAccountNumber;
    private Date transactionDate;

    public Transaction(String tranasactionTitle, int quota, int senderAccountNumber, int destinationAccountNumber) {
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

    public int getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(int senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public int getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(int destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }



}
