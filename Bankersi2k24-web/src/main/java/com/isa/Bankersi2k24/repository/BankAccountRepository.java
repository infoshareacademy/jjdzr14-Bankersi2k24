package com.isa.Bankersi2k24.repository;

import com.isa.Bankersi2k24.DAO.FileName;
import com.isa.Bankersi2k24.DAO.Serializable;
import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.models.BankAccountNumber;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Predicate;

@Repository
public class BankAccountRepository {
    private List<BankAccount> bankAccounts;

    public BankAccountRepository() {
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

    public void updateBankAccount(BankAccount bankAccount) throws Exception {
        int index = this.bankAccounts.indexOf(bankAccount);
        if(index < 0) {
            throw new Exception(String.format("Bank account of id: %d does not exist", bankAccount.getId()));
        } else{
            this.bankAccounts.set(index, bankAccount);
            this.saveAllObjects(this.bankAccounts);
        }
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
