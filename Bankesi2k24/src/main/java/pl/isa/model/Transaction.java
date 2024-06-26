package pl.isa.model;

import java.util.Date;

public class Transaction {
    private String tranasactionTitle;
    private int quota;
    private int senderAccountNumber;
    private int destinationAccountNumber;
    private Date transactionDate;

    public Transaction(String tranasactionTitle, int quota, int senderAccountNumber, int destinationAccountNumber) {
        this.tranasactionTitle = tranasactionTitle;
        this.quota = quota;
        this.senderAccountNumber = senderAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;


    }
    private boolean verifyTransaction(BankAccount sender) throws RuntimeException{
        if(sender.getAvailableQuota() < this.quota){
            throw new RuntimeException("Not enough quota on sender account");
        }
        return true;
    }

    public boolean triggerTransaction(){
        BankAccount sender = new BankAccount(this.senderAccountNumber);
        BankAccount recepient = new BankAccount(this.destinationAccountNumber);
        if(this.verifyTransaction(sender)){
            sender.setAvailableQuota(sender.getAvailableQuota()-quota);
            this.transactionDate = new Date();
            sender.addToTransactionList(this);
            recepient.addToTransactionList(this);
            return true;
        }
        return false;
    }
}
