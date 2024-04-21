package pl.isa.model;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {
    private String title;

    private Integer accountNumber;

    private Double transactionAmount;

    private Date created;

    private Integer beneficiaryAccount;

    public Transaction() {
    }

    public Transaction(String title, Integer accountNumber, Double transactionAmount, Date created, Integer beneficiaryAccount) {
        this.title = title;
        this.accountNumber = accountNumber;
        this.transactionAmount = transactionAmount;
        this.created = created;
        this.beneficiaryAccount = beneficiaryAccount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getBeneficiaryAccount() {
        return beneficiaryAccount;
    }

    public void setBeneficiaryAccount(Integer beneficiaryAccount) {
        this.beneficiaryAccount = beneficiaryAccount;
    }
}
