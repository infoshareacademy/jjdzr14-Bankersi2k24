package pl.isa.model;

import java.util.Date;

/**
 * this class is temporary, purely for implementing the Conncector class
 */
public class PlainOldJavaObject {
    private String userName;
    private int accountNumber;
    private Date bornDate;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    @Override
    public String toString() {
        return "PlainOldJavaObject{" +
                "userName='" + userName + '\'' +
                ", accountNumber=" + accountNumber +
                ", bornDate=" + bornDate +
                '}';
    }
}
