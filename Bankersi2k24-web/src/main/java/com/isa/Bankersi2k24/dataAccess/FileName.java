package com.isa.Bankersi2k24.dataAccess;

public enum FileName {
    USER("users.json"),
    TRANSACITON("transactions.json"),
    BANKACCOUNT("bank_accounts.json");

    private final String name;

    FileName(String fileName) {
        this.name = fileName;
    }
}
