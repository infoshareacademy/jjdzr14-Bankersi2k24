package com.isa.Bankersi2k24.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class Dashboard {
    private Integer numberOfAccounts;
    private List<BankAccount> accounts;
    private Map<Currencies, BigDecimal> quotaPerCurrency;
    private String userName;
    private Map<Currencies, String> currencyIcons;

    public Dashboard() {
        this.accounts = new ArrayList<>();
        this.quotaPerCurrency = new HashMap<>();

        this.currencyIcons = new HashMap<>();
        this.currencyIcons.put(Currencies.EUR, "static/img/eur.png");
        this.currencyIcons.put(Currencies.USD, "static/img/dol.png");
    }
}
