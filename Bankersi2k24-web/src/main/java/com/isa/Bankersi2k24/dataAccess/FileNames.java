package com.isa.Bankersi2k24.dataAccess;

public enum FileNames {
    USER("users.json"),
    TRANSACITON("transactions.json"),
    BANKACCOUNT("bank_accounts.json");


    FileNames(String fileName) {
        this.name = fileName;
    };

    private String name;

    @Override
    public String toString() {
        return name;
    }
}
