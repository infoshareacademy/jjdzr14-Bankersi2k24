package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.models.BankAccountNumber;

import java.util.Random;

public class BankAccountNumberService {

    public static final String bankAccountRegexp = "(^\\d{2,26}$)|(^[\\d]{2}\\s[\\d]{4}\\s[\\d]{4}\\s[\\d]{4}\\s[\\d]{4}\\s[\\d]{4}\\s[\\d]{4}$)";
    public void setBankAccountNumber(BankAccount bankAccount, String bankAccountString){
        bankAccount.setBankAccountNumber(bankAccountString);
    }

    public void setBankAccountNumber(BankAccount bankAccount, BankAccountNumber bankAccountNumber){
        bankAccount.setBankAccountNumber(bankAccountNumber.toString());
    }

    public static BankAccountNumber generateRandomBankAccountNumber(){
        Random rnd = new Random();
        BankAccountNumber ban = new BankAccountNumber();

            ban.setBankAccountNumber(
                    rnd.ints(0, 99).findFirst().getAsInt(),
                    rnd.ints(0, 9999).findFirst().getAsInt(),
                    rnd.ints(0, 9999).findFirst().getAsInt(),
                    rnd.ints(0, 9999).findFirst().getAsInt(),
                    rnd.ints(0, 9999).findFirst().getAsInt(),
                    rnd.ints(0, 9999).findFirst().getAsInt(),
                    rnd.ints(0, 9999).findFirst().getAsInt()
            );

        return ban;
    }

    public static BankAccountNumber accountNumberStringToBan(String bankAccountNumber){
        String [] groups = bankAccountNumber.split(" ");
        if(groups.length == 0 || groups.length > 8){
            throw new RuntimeException(String.format("Could not translate string: %s to bank account.", bankAccountNumber));
        }
        BankAccountNumber ban = new BankAccountNumber();

        ban.setBankAccountNumber(
                Integer.parseInt(groups[0]),
                Integer.parseInt(groups[1]),
                Integer.parseInt(groups[2]),
                Integer.parseInt(groups[3]),
                Integer.parseInt(groups[4]),
                Integer.parseInt(groups[5]),
                Integer.parseInt(groups[6])
                );
        return ban;
    }

}
