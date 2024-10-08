package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.models.*;
import com.isa.Bankersi2k24.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final UserService userService;

    public BankAccountService(BankAccountRepository bankAccountRepository, UserService userService) {
        this.bankAccountRepository = bankAccountRepository;
        this.userService = userService;
    }

    public List<BankAccount> getBankAccountsForUser(BigInteger userId){
        var bankAccounts = bankAccountRepository.findBankAccountByUserId(userId);
        return (bankAccounts != null) ?  bankAccounts : Collections.emptyList();
    }

    public BankAccount getBankAccount(BigInteger accountId) throws Exception {
        return null;
//        return this.bankAccountRepository.fetchAllBankAccounts().stream().filter(
//                ba -> Objects.equals(ba.getId(), accountId))
//                .findFirst().orElseThrow();
    }

    public BankAccount getBankAccount(String ban) {
        return getBankAccount(BankAccountNumberService.accountNumberStringToBan(ban));
    }

    public BankAccount getBankAccount(BankAccountNumber ban) {
        return null;
//        return this.bankAccountRepository.getBankAccount(ban);
    }

    public BankAccount createNewBankAccountForUser(User user, BigDecimal quota, Currencies currency){
        BankAccountNumber bankAccountNumber;
        do bankAccountNumber = BankAccountNumberService.generateRandomBankAccountNumber();
        while(!isBankAccountNumberUnique(bankAccountNumber));
        return BankAccount.builder()
                .user(user)
                .bankAccountNumber(bankAccountNumber.toString())
                .availableQuota(quota)
                .currency(currency)
                .build();
    }

    private boolean isBankAccountNumberUnique(BankAccountNumber bankAccountNumber){
        return bankAccountRepository.findBankAccountByBankAccountNumber(bankAccountNumber.toString()).isEmpty();
    }

    public void saveBankAccount(BankAccount bankAccount){
        this.bankAccountRepository.save(bankAccount);
    }

    public boolean deleteBankAccount(BankAccountNumber bankAccountNumber) throws Exception {
        // need to perform checks if there are any transactions assosiated with this BAN
//        if(this.bankAccountRepository.queryBankAccounts(ba -> (ba.getBankAccountNumber() == bankAccountNumber &&
//                                                                    ba.getTransactionList()
//                                                                            .stream()
//                                                                            .anyMatch(t -> !t.isComplete())))){
//            throw new Exception(String.format("Bank account of number %s has open transactions and cannot be deleted",
//                    bankAccountNumber.toString()));
//        }else {
//            if(this.bankAccountRepository.deleteBankAccount(bankAccountNumber))
//                return true;
//            else
//                throw new Exception(String.format("Could not delete bank account %s from DB",
//                        bankAccountNumber.toString()));
//        }
        return false;
    }

    public boolean deleteBankAccount(String ban) throws Exception {
        return this.deleteBankAccount(BankAccountNumberService.accountNumberStringToBan(ban));
    }

//    public void addToTransactionList(BankAccount bankAccount, Transaction transaction) {
//        if(!bankAccount.getTransactions().contains(transaction))
//            bankAccount.getTransactions().add(transaction);
//
//        try {
////            bankAccountRepository.updateBankAccount(bankAccount);
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }

    public Dashboard prepareUserDashboard(BigInteger userId) {
        Dashboard dashboard = Dashboard.builder()
                .accounts(getBankAccountsForUser(userId))
                .userName(userService.getUserName(userId))
                .numberOfAccounts(getBankAccountsForUser(userId).size())
                .quotaPerCurrency(new HashMap<>())
                .build();

        for(BankAccount ba : dashboard.getAccounts()){
            if(dashboard.getQuotaPerCurrency().containsKey(ba.getCurrency())){
                // get obj at key, add new quota to total sum
                BigDecimal ret = ba.getAvailableQuota();
                dashboard.getQuotaPerCurrency().replace(
                    ba.getCurrency(),
                    ret.add(dashboard.getQuotaPerCurrency().get(ba.getCurrency()))
                );
            }else
                dashboard.getQuotaPerCurrency().put(
                        ba.getCurrency(),
                        ba.getAvailableQuota()
                );
        }
        return dashboard;
    }

    public List<BankAccount> getAllBankAccounts() {
        return this.bankAccountRepository.findAll();
    }
}
