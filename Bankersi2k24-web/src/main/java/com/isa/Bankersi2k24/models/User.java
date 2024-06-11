package com.isa.Bankersi2k24.models;

import com.isa.Bankersi2k24.dataAccess.FileNames;
import com.isa.Bankersi2k24.dataAccess.Serializable;
import com.isa.Bankersi2k24.services.UserService;

import java.util.Date;
import java.util.Objects;

public class User extends Serializable<User> {
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
        super(FileNames.USER, User.class);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getName(), user.getName()) && Objects.equals(getLogin(), user.getLogin()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getCreationDate(), user.getCreationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLogin(), getEmail(), getCreationDate());
    }
}
