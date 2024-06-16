package com.isa.Bankersi2k24.repository;

import com.isa.Bankersi2k24.DAO.FileName;
import com.isa.Bankersi2k24.DAO.Serializable;
import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.models.BankAccountNumber;
import com.isa.Bankersi2k24.services.BankAccountNumberService;
import com.isa.Bankersi2k24.services.BankAccountService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BankAccountRepository extends Serializable {
    private List<BankAccount> bankAccounts;
    public BankAccountRepository() {
        super(FileName.BANKACCOUNT, BankAccount.class);
        this.bankAccounts = fetchAllObjects();
    }

    public List<BankAccount> fetchAllBankAccounts() {

        return this.bankAccounts;
    }

    public BankAccount getBankAccount(BankAccountNumber ban){
        return this.bankAccounts.stream()
                .filter(b -> b.getBankAccountNumber() == ban)
                .findFirst()
                .orElseThrow();
    }

    public void saveNewBankAccount(BankAccount ban){
        this.save(ban);
    }

    public void updateBankAccount(BankAccount ban){
        BankAccount tmpBankAccount = getBankAccount(ban.getBankAccountNumber());
        tmpBankAccount = ban;
        this.save();
    }

    public boolean queryBankAccounts(Predicate<BankAccount> predicate){
        return !this.bankAccounts.stream().noneMatch(predicate);
    }
}
