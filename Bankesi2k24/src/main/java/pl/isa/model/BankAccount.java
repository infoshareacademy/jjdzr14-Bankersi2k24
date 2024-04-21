package pl.isa.model;

public class BankAccount {
    private Double currentBalance;
    private String customerName;
    private Integer accountNumber;
    public BankAccount() {

    }

    public BankAccount(Double currentBalance, String customerName, Integer accountNumber) {
        this.currentBalance = currentBalance;
        this.customerName = customerName;
        this.accountNumber = accountNumber;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    }
