package pl.isa.model;

import pl.isa.services.UserService;
import java.util.*;

public class User {
    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;
    private BankAccount bankAccount;
    private String name ;
    private String login;
    private String password;
    private String lastName;
    private String email;

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
