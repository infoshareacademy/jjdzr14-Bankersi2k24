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
@Table(name = "bankaccounts")
public class BankAccount{
    @Id
    @GeneratedValue
    private BigInteger id;
    private BigDecimal availableQuota;
    private Currencies currency;

    @OneToMany(mappedBy = "id", cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    private List<Transaction> transactions;

//    @Convert(converter = String.class)
    private String bankAccountNumber;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "bankAccount_id", nullable = false)
    private User user;

    public BankAccount() {
        this.transactions = new ArrayList<>();
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
