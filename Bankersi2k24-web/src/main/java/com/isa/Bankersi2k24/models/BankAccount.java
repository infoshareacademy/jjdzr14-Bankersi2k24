package com.isa.Bankersi2k24.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bankaccounts")
public class BankAccount{
    @Id
    @GeneratedValue
    private BigInteger id;
    private BigDecimal availableQuota;
    private Currencies currency;

    @OneToMany(mappedBy = "transaction_id", cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    private List<Transaction> transactions = new ArrayList<>();

    private String bankAccountNumber;

    @ManyToOne(cascade = { CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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
