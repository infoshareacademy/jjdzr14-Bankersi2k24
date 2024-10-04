package com.isa.Bankersi2k24.models;

public enum Currencies {
    PLN("Polish Złoty"),
    USD("US American Dolar"),
    AUD("Australian Dolar"),
    EUR("Euro"),
    NONE("None");

    private final String name;
    Currencies(String c) {
        this.name = c;
    }

}
