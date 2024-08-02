package com.isa.Bankersi2k24.models;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankAccount extends Entity{
    private Integer availableQuota;
    private Currencies currency;
    private List<BigInteger> outGoingTransactionList;
    private List<BigInteger> incomingTransactionList;
    private BankAccountNumber bankAccountNumber;
    private BigInteger userId;

    public BankAccount() {
        this.outGoingTransactionList = new ArrayList<>();
        this.incomingTransactionList = new ArrayList<>();
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public Currencies getCurrency() {
        return currency;
    }

    public void setCurrency(Currencies currency) {
        this.currency = currency;
    }

    public Integer getAvailableQuota() {
        return this.availableQuota;
    }

    public void setAvailableQuota(Integer availableQuota) {
        this.availableQuota = availableQuota;
    }
    public List<BigInteger> getOutGoingTransactionList() {
        return outGoingTransactionList;
    }

    public void setOutGoingTransactionList(List<BigInteger> outGoingTransactionList) {
        this.outGoingTransactionList = outGoingTransactionList;
    }

    public List<BigInteger> getIncomingTransactionList() {
        return incomingTransactionList;
    }

    public void setIncomingTransactionList(List<BigInteger> incomingTransactionList) {
        this.incomingTransactionList = incomingTransactionList;
    }


    public BankAccountNumber getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(BankAccountNumber bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return getCurrency() == that.getCurrency() && Objects.equals(getBankAccountNumber(), that.getBankAccountNumber()) && Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrency(), getBankAccountNumber(), getUserId());
    }
}
