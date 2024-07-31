package com.isa.Bankersi2k24.repository;

import com.isa.Bankersi2k24.DAO.FileName;
import com.isa.Bankersi2k24.DAO.Serializable;
import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.models.BankAccountNumber;
import java.util.List;
import java.util.function.Predicate;

public class BankAccountRepository extends Serializable<BankAccount> {
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
                .filter(b -> b.getBankAccountNumber().equals(ban))
                .findFirst()
                .orElseThrow();
    }

    public void saveNewBankAccount(BankAccount ban){
        this.save(ban);
        this.invalidateBankAccountList();
    }

    public boolean queryBankAccounts(Predicate<BankAccount> predicate){
        return !this.bankAccounts.stream().noneMatch(predicate);
    }

    public boolean deleteBankAccount(BankAccountNumber ban){
        return this.bankAccounts.removeIf(ba -> ba.getBankAccountNumber() == ban);
    }

    private void invalidateBankAccountList(){
        this.bankAccounts = fetchAllObjects();
    }
}
