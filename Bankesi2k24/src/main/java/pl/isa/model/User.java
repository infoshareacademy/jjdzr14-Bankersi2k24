package pl.isa.model;

public class User {
    private BankAccount bankAccount;
    private String name ;
    private String login;
    private String password;
    private String lastName;
  
   public User() {}

    public User() {
        this.bankAccount = new BankAccount();
    }

    public void createFakeBankAccount(int quota, Currencies curr){
        this.bankAccount = BankAccount.createFakeBankAccount(quota, curr);
    }
    public BankAccount getBankAccount() {
        return bankAccount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;

    }
}
