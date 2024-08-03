package com.isa.Bankersi2k24.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction extends Entity{
    private String transactionTitle;
    private int quota;
    private Currencies currency;
    private BankAccountNumber senderAccountNumber;
    private BankAccountNumber destinationAccountNumber;
    private LocalDateTime transactionDate;
    private boolean isComplete;
    private Integer trackingNumber;

    public Transaction() {
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
        return trackingNumber;
    }

    public void setTrackingNumber() {
        this.trackingNumber = this.hashCode();
        if(this.trackingNumber < 0 ) this.trackingNumber *= -1;
    }

    public Currencies getCurrency() {
        return currency;
    }

    public void setCurrency(Currencies currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return getQuota() == that.getQuota() && Objects.equals(getTransactionTitle(), that.getTransactionTitle()) && Objects.equals(getSenderAccountNumber(), that.getSenderAccountNumber()) && Objects.equals(getDestinationAccountNumber(), that.getDestinationAccountNumber()) ;
    }

    @Override
    public int hashCode() {
        //if(this.trackingNumber == null)
            return Objects.hash(getTransactionTitle(), getQuota(), getSenderAccountNumber(), getDestinationAccountNumber());
//        else
//            return Objects.hash(getTransactionTitle(), getQuota(), getSenderAccountNumber(), getDestinationAccountNumber(), getTrackingNumber(), getId());
    }
}
