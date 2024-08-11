package com.isa.Bankersi2k24.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dashboard {
    private Integer numberOfAccounts;
    private List<BankAccount> accounts;
    private Map<Currencies, Integer> quotaPerCurrency;
    private String userName;
    private Map<Currencies, String> currencyIcons;

    public Dashboard() {
        this.accounts = new ArrayList<>();
        this.quotaPerCurrency = new HashMap<>();

        this.currencyIcons = new HashMap<>();
        this.currencyIcons.put(Currencies.EUR, "static/img/eur.png");
        this.currencyIcons.put(Currencies.USD, "static/img/dol.png");
    }

    public Integer getNumberOfAccounts() {
        return numberOfAccounts;
    }

    public void setNumberOfAccounts(Integer numberOfAccounts) {
        this.numberOfAccounts = numberOfAccounts;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<Currencies, String> getCurrencyIcons() {
        return currencyIcons;
    }
    public void setCurrencyIcons(Map<Currencies, String> currencyIcons) {
        this.currencyIcons = currencyIcons;
    }

    public Map<Currencies, Integer> getQuotaPerCurrency() {
        return quotaPerCurrency;
    }

    public void setQuotaPerCurrency(Map<Currencies, Integer> quotaPerCurrency) {
        this.quotaPerCurrency = quotaPerCurrency;
    }
}
