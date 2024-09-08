package com.isa.Bankersi2k24.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Data
@Entity
@Table(name = "transactions")
public class Transaction{
    @Id
    @GeneratedValue
    private BigInteger id;

    @NotNull(message = "Title cannot be empty")
    private String transactionTitle;

    @NotNull(message = "Transaction amount cannot be empty or 0 or negative")
    @Min(0)
    private BigDecimal quota;

    @NotNull
    private Currencies currency;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "senderBankaccount_id")
    private BankAccount senderBankAccount;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "destinationBankaccount_id")
    private BankAccount destinationBankAccount;

    private LocalDateTime transactionDate;
    private boolean isComplete;
    private Integer trackingNumber;

//    @ManyToMany(mappedBy = "transactions")
//    private List<BankAccount> bankAccounts;


    public Transaction() {
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void setTrackingNumber() {
        this.trackingNumber = this.hashCode();
        if(this.trackingNumber < 0 ) this.trackingNumber *= -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return getQuota().equals(that.getQuota()) && Objects.equals(getTransactionTitle(), that.getTransactionTitle()) && Objects.equals(getSenderBankAccount(), that.getSenderBankAccount()) && Objects.equals(getDestinationBankAccount(), that.getDestinationBankAccount()) ;
    }

    @Override
    public int hashCode() {
            return Objects.hash(getTransactionTitle(), getQuota(), getSenderBankAccount(), getDestinationBankAccount());
    }

}
