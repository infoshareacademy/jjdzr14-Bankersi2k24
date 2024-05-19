package pl.isa.model;

public enum Currencies {
    PLN("Polish Złoty"),
    USD("US American Dolar"),
    AUD("Australian Dolar"),
    EUR("Euro");

    private String name;
    Currencies(String c) {
        this.name = c;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
