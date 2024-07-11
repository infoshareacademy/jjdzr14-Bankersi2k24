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
        //List<String> ret = new ArrayList<>();
//        String input = bankAccountNumber.replaceAll("\\s", "");
//        Pattern pattern = Pattern.compile("^(\\d{2})(\\d{4})(\\d{4})(\\d{4})(\\d{4})(\\d{4})(\\d{4})");
//        Matcher matcher = pattern.matcher(input);
//        if (matcher.matches()) {
//            BankAccountNumber ban = new BankAccountNumber();
//            ban.setBankAccountNumber(
//                    Integer.getInteger(matcher.group(1)),
//                    Integer.getInteger(matcher.group(2)),
//                    Integer.getInteger(matcher.group(3)),
//                    Integer.getInteger(matcher.group(4)),
//                    Integer.getInteger(matcher.group(5)),
//                    Integer.getInteger(matcher.group(6)),
//                    Integer.getInteger(matcher.group(7)));
//
//            return ban;
//        }
//        throw new RuntimeException(String.format("Could not translate string: %s to bank account.", bankAccountNumber));
        String [] groups = bankAccountNumber.split(" ");
//        String tmp = groups[0];
//        Integer tmp2 = Integer.parseInt(tmp);

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
