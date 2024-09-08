package com.isa.Bankersi2k24.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


@Data
@Entity
@Table
public class Transaction{
    @Id
    private Long id;

    @NotNull(message = "Title cannot be empty")
    private String transactionTitle;

    @NotNull(message = "Transaction amount cannot be empty or 0 or negative")
    @Min(0)
    private BigDecimal quota;

    @NotNull
    private Currencies currency;

    @NotNull
    private BankAccountNumber senderAccountNumber;

    @NotNull
    private BankAccountNumber destinationAccountNumber;

    private LocalDateTime transactionDate;
    private boolean isComplete;
    private Integer trackingNumber;


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
        return getQuota().equals(that.getQuota()) && Objects.equals(getTransactionTitle(), that.getTransactionTitle()) && Objects.equals(getSenderAccountNumber(), that.getSenderAccountNumber()) && Objects.equals(getDestinationAccountNumber(), that.getDestinationAccountNumber()) ;
    }

    @Override
    public int hashCode() {
            return Objects.hash(getTransactionTitle(), getQuota(), getSenderAccountNumber(), getDestinationAccountNumber());
    }

}
