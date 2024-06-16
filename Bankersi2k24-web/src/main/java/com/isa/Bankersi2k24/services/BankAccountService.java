package com.isa.Bankersi2k24.services;

import com.fasterxml.jackson.databind.node.BaseJsonNode;
import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.models.BankAccountNumber;
import com.isa.Bankersi2k24.models.Currencies;
import com.isa.Bankersi2k24.models.Transaction;
import com.isa.Bankersi2k24.repository.BankAccountRepository;

import java.util.List;

public class BankAccountService {
    public static BankAccount getBankAccount(String ban) {
        return getBankAccount(BankAccountNumberService.accountNumberStringToBan(ban));
    }

    public static BankAccount getBankAccount(BankAccountNumber ban) {
        BankAccountRepository bankAccountRepository = new BankAccountRepository();
        return bankAccountRepository.getBankAccount(ban);
    }

    public BankAccount createNewBankAccount(Integer forUserId){
        BankAccount ban = new BankAccount();
        ban.setUserId(forUserId);
        ban.setBankAccountNumber(BankAccountNumberService.generateRandomBankAccountNumber());
        return ban;
    }

    public void saveBankAccount(BankAccount bankAccount){
        BankAccountRepository bankAccountRepository = new BankAccountRepository();
        bankAccountRepository.saveNewBankAccount(bankAccount);
    }

    public boolean deleteBankAccount(BankAccountNumber bankAccountNumber) {
        return false;
    }

    public boolean deleteBankAccount(String ban) {
        return false;
    }

    public boolean updateBankAccount(BankAccount bankAccount) {
        return false;
    }

    public boolean updateBankAccount(String bankAccount) {
        return false;
    }

    public static boolean addToTransactionList(BankAccount bankAccount, Transaction transaction) {
        if(bankAccount.getTransactionList() != null){
            bankAccount.getTransactionList().add(transaction);
            return true;
        }else{
            //try reading from DB again? and then  save
            return false;
        }
    }

}
