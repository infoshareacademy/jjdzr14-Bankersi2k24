package com.isa.Bankersi2k24.DAO;

public enum FileName {
    USER("users.json"),
    TRANSACITON("transactions.json"),
    BANKACCOUNT("bank_accounts.json");

    public String getName() {
        return name;
    }

    private final String name;

    FileName(String fileName) {
        this.name = fileName;
    }
}
