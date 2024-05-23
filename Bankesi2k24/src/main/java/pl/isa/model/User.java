package pl.isa.model;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
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

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public User() {
        this.bankAccount = new BankAccount();
        this.creationDate = new Date();
    }

    public void createFakeBankAccount(int quota, Currencies curr){
        this.bankAccount = BankAccount.createFakeBankAccount(quota, curr);
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
        if(this.verifyEmail(email)) {
            this.email = email;
            return true;
        }
        else {
            System.out.println("Not a valid email address");
            this.email = "";
            return false;
        }
    }

    public static boolean verifyEmail(String email){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.matches();
    }
}
