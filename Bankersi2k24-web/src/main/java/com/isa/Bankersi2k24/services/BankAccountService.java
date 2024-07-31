package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.models.BankAccountNumber;
import com.isa.Bankersi2k24.models.Transaction;
import com.isa.Bankersi2k24.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountService() {
        this.bankAccountRepository = new BankAccountRepository();
    }

    public BankAccount getBankAccount(String ban) {
        return getBankAccount(BankAccountNumberService.accountNumberStringToBan(ban));
    }

    public BankAccount getBankAccount(BankAccountNumber ban) {
        return this.bankAccountRepository.getBankAccount(ban);
    }

    public BankAccount createNewBankAccount(Integer forUserId){
        BankAccount ban = new BankAccount();
        ban.setUserId(forUserId);
        ban.setBankAccountNumber(BankAccountNumberService.generateRandomBankAccountNumber());

        return ban;
    }

    public void saveBankAccount(BankAccount bankAccount){
        this.bankAccountRepository.saveNewBankAccount(bankAccount);
    }

    public boolean updateBankAccount(BankAccount bankAccount) throws Exception {
        if(this.bankAccountRepository.queryBankAccounts(ba->ba == bankAccount)){
            this.bankAccountRepository.updateBankAccount(bankAccount);
            return true;
        }else{
            throw new Exception(String.format("Such bank account (number: %s) does not exist",
                    bankAccount.getBankAccountNumber()));
        }
    }

    public boolean deleteBankAccount(BankAccountNumber bankAccountNumber) throws Exception {
        // need to perform checks if there are any transactions assosiated with this BAN
        if(this.bankAccountRepository.queryBankAccounts(ba -> (ba.getBankAccountNumber() == bankAccountNumber &&
                                                                    ba.getTransactionList()
                                                                            .stream()
                                                                            .anyMatch(t -> !t.isComplete())))){
            throw new Exception(String.format("Bank account of number %s has open transactions and cannot be deleted",
                    bankAccountNumber.toString()));
        }else {
            if(this.bankAccountRepository.deleteBankAccount(bankAccountNumber))
                return true;
            else
                throw new Exception(String.format("Could not delete bank account %s from DB",
                        bankAccountNumber.toString()));
        }
    }

    public boolean deleteBankAccount(String ban) throws Exception {
        return this.deleteBankAccount(BankAccountNumberService.accountNumberStringToBan(ban));
    }

    public void addToTransactionList(BankAccount bankAccount, Transaction transaction) {
        bankAccount.getTransactionList().add(transaction);
    }

}
