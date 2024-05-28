package pl.isa.model;

import pl.isa.services.UserService;
import java.util.*;

public class User {
    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    private BankAccount bankAccount;
    private String name ;
    private String login;
    private String password;
    private String lastName;
    private String email;
    private String pesel;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    private Date creationDate;

    public User() {
        this.bankAccount = new BankAccount();
        this.creationDate = new Date();
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

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

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        if(UserService.verifyEmail(email)) {
            this.email = email;
            return true;
        }
        else {
            System.out.println("Not a valid email address");
            this.email = "";
            return false;
        }
    }


}
