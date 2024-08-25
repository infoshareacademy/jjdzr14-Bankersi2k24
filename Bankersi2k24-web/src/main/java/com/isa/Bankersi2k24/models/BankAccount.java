package com.isa.Bankersi2k24.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@Entity
public class BankAccount{
    @Id
    @GeneratedValue
    private BigInteger id;
    private BigDecimal availableQuota;
    private Currencies currency;

    @ElementCollection
    //https://www.baeldung.com/java-jpa-persist-string-list#elementcollection
    private List<BigInteger> outGoingTransactionList;
    @ElementCollection
    //https://www.baeldung.com/java-jpa-persist-string-list#elementcollection
    private List<BigInteger> incomingTransactionList;

    @Convert(converter = String.class)
    private BankAccountNumber bankAccountNumber;

    @OneToOne
    private User user;

    public BankAccount() {
        this.outGoingTransactionList = new ArrayList<>();
        this.incomingTransactionList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return getCurrency() == that.getCurrency() && Objects.equals(getBankAccountNumber(), that.getBankAccountNumber()) && Objects.equals(this.user.getId(), that.user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrency(), getBankAccountNumber(), this.user.getId());
    }
}
