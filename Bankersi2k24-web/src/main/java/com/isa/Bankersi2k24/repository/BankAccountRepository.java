package com.isa.Bankersi2k24.repository;

import com.isa.Bankersi2k24.DAO.FileName;
import com.isa.Bankersi2k24.DAO.Serializable;
import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.models.BankAccountNumber;
import com.isa.Bankersi2k24.services.BankAccountNumberService;
import com.isa.Bankersi2k24.services.BankAccountService;

import java.util.ArrayList;
import java.util.List;

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
}
