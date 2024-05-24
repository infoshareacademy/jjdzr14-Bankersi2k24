package pl.isa.model;

import pl.isa.dataAccess.Connector;
import pl.isa.dataAccess.FileNames;
import pl.isa.dataAccess.ObjectToJson;

import java.util.*;
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

    public static User findUser(String login){
        Connector connector = new Connector(FileNames.USER.toString());
        ObjectToJson objectToJson = new ObjectToJson<User>();
        List<User> users;

        users = objectToJson.deserialize(connector.read(), User.class);

        for(User u : users){
            if(login.equals(u.getLogin())){
                return u;
            }
        }
        return null;
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

    private boolean verifyLogin(String login){
        return Objects.equals(this.login, login);
    }

    private boolean verifyPassword(String password){
        return Objects.equals(this.password, password);
    }

    public boolean verifyCredentials(String login, String password){
        if(this.verifyLogin(login)) return this.verifyPassword(password);
        else return false;

    }
}
