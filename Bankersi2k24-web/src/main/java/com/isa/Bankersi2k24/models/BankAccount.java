package com.isa.Bankersi2k24.models;

import com.isa.Bankersi2k24.DAO.FileName;
import com.isa.Bankersi2k24.DAO.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BankAccount {
    private Integer accountNumber;
    private Integer availableQuota;
    private Currencies currency;
    private List<Transaction> transactionList;
    private BankAccountNumber bankAccountNumber;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private Integer userId;

    public BankAccount() {}

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

    public BankAccountNumber getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(BankAccountNumber bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
}
