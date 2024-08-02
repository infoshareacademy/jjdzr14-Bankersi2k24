package com.isa.Bankersi2k24.repository;

import com.isa.Bankersi2k24.DAO.FileName;
import com.isa.Bankersi2k24.DAO.Serializable;
import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.models.BankAccountNumber;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class BankAccountRepository extends Serializable {
    private List<BankAccount> bankAccounts;
    private static BankAccountRepository INSTANCE = null;

    public static BankAccountRepository BankAccountRepository(){
        if(INSTANCE == null) {
            INSTANCE = new BankAccountRepository();
        }
        return INSTANCE;
    }
    private BankAccountRepository() {
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

    public void updateBankAccount(BankAccount bankAccount){
        this.bankAccounts.set(
                this.bankAccounts.indexOf(bankAccount),
                bankAccount
        );
        this.save();
        this.invalidateBankAccountList();
    }

    public boolean queryBankAccounts(Predicate<BankAccount> predicate){
        return !this.bankAccounts.stream().noneMatch(predicate);
    }
    public BankAccount bankAccountByUserID(Predicate<BankAccount> predicate){
        return this.bankAccounts.stream().filter(predicate).collect(Collectors.toList()).get(0);
    }


    public boolean deleteBankAccount(BankAccountNumber ban){
        return this.bankAccounts.removeIf(ba -> ba.getBankAccountNumber() == ban);
    }

    private void invalidateBankAccountList(){
        this.bankAccounts = fetchAllObjects();
    }
}
