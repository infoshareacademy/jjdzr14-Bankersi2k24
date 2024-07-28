package com.isa.Bankersi2k24.models;

import com.isa.Bankersi2k24.DAO.FileName;
import com.isa.Bankersi2k24.DAO.Serializable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Transaction extends Serializable<Transaction> {
    private String transactionTitle;
    private int quota;
    private BankAccountNumber senderAccountNumber;
    private BankAccountNumber destinationAccountNumber;
    private LocalDateTime transactionDate;
    private boolean isComplete;
    private Integer trackingNumber;


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

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getTrackingNumber() {
        this.trackingNumber = this.hashCode();
        return trackingNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return getQuota() == that.getQuota() && isComplete() == that.isComplete() && Objects.equals(getTransactionTitle(), that.getTransactionTitle()) && Objects.equals(getSenderAccountNumber(), that.getSenderAccountNumber()) && Objects.equals(getDestinationAccountNumber(), that.getDestinationAccountNumber()) && Objects.equals(getTransactionDate(), that.getTransactionDate()) && Objects.equals(trackingNumber, that.trackingNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransactionTitle(), getQuota(), getSenderAccountNumber(), getDestinationAccountNumber(), getTransactionDate());
    }
}
