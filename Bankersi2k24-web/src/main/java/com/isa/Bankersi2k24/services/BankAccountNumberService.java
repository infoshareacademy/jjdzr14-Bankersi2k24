package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.models.BankAccountNumber;
import com.isa.Bankersi2k24.repository.BankAccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankAccountNumberService {
    public void setBankAccountNumber(BankAccount bankAccount, String bankAccountString){
        bankAccount.setBankAccountNumber(accountNumberStringToBan(bankAccountString));
    }

    public void setBankAccountNumber(BankAccount bankAccount, BankAccountNumber bankAccountNumber){
        bankAccount.setBankAccountNumber(bankAccountNumber);
    }

    public static BankAccountNumber generateRandomBankAccountNumber(){
        Random rnd = new Random();
        BankAccountNumber ban = new BankAccountNumber();
        do {
            ban.setBankAccountNumber(
                    rnd.ints(0, 99).findFirst().getAsInt(),
                    rnd.ints(0, 9999).findFirst().getAsInt(),
                    rnd.ints(0, 9999).findFirst().getAsInt(),
                    rnd.ints(0, 9999).findFirst().getAsInt(),
                    rnd.ints(0, 9999).findFirst().getAsInt(),
                    rnd.ints(0, 9999).findFirst().getAsInt()
            );
        }
        while(checkIfBANexists(ban));
        return ban;
    }

    public static boolean checkIfBANexists(BankAccountNumber bankAccountNumber){
        BankAccountRepository bankAccountRepository = BankAccountRepository.BankAccountRepository();
        return bankAccountRepository.queryBankAccounts(b -> b.getBankAccountNumber() == bankAccountNumber);
    }

    public static BankAccountNumber accountNumberStringToBan(String bankAccountNumber){
        List<String> ret = new ArrayList<>();
        String input = bankAccountNumber.replaceAll("\\s", "");
        Pattern pattern = Pattern.compile("^(.{2})(.{4})(.{4})(.{4})(.{4})(.{4}).*");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                ret.add(matcher.group(i));
            }
        }
        if(matcher.groupCount() != 4 )
            throw new RuntimeException(String.format("Could not translate string: %s to bank account.", bankAccountNumber));

        BankAccountNumber ban = new BankAccountNumber();
        ban.setBankAccountNumber(
                Integer.getInteger(ret.get(0)),
                Integer.getInteger(ret.get(1)),
                Integer.getInteger(ret.get(2)),
                Integer.getInteger(ret.get(3)),
                Integer.getInteger(ret.get(4)),
                Integer.getInteger(ret.get(5)));

        return ban;
    }

}
