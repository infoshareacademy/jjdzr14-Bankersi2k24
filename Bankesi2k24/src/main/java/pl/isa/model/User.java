package pl.isa.model;

public class User {
    private BankAccount bankAccount;

    public User() {
        this.bankAccount = new BankAccount();
    }

    public void createFakeBankAccount(int quota, Currencies curr){
        this.bankAccount = BankAccount.createFakeBankAccount(quota, curr);
    }
    public BankAccount getBankAccount() {
        return bankAccount;
    }
}
