package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.controller.AccountControler;
import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.models.BankAccountNumber;
import com.isa.Bankersi2k24.models.Dashboard;
import com.isa.Bankersi2k24.models.Transaction;
import com.isa.Bankersi2k24.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountService() {
        this.bankAccountRepository = new BankAccountRepository();
    }

    public List<BankAccount> getBankAccountsForUser(BigInteger userId){
        return this.bankAccountRepository.fetchAllBankAccounts().stream().filter (
                ba -> ba.getUser().getId().equals(userId)).collect(Collectors.toList());
    }

    public BankAccount getBankAccount(BigInteger accountId) throws Exception {
        return this.bankAccountRepository.fetchAllBankAccounts().stream().filter(
                ba -> Objects.equals(ba.getId(), accountId))
                .findFirst().orElseThrow();
    }

    public BankAccount getBankAccount(String ban) {
        return getBankAccount(BankAccountNumberService.accountNumberStringToBan(ban));
    }

    public BankAccount getBankAccount(BankAccountNumber ban) {
        return this.bankAccountRepository.getBankAccount(ban);
    }

    public BankAccount createNewBankAccount(BigInteger forUserId){
        BankAccount ban = new BankAccount();
        ban.setUserId(forUserId);
        ban.setBankAccountNumber(BankAccountNumberService.generateRandomBankAccountNumber());

        return ban;
    }

    public void saveBankAccount(BankAccount bankAccount){
        this.bankAccountRepository.saveNewBankAccount(bankAccount);
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

    public void addToTransactionList(BankAccount bankAccount, Transaction transaction) {
        if(bankAccount.getBankAccountNumber().equals(transaction.getDestinationAccountNumber())) {
            if(!bankAccount.getIncomingTransactionList().contains(transaction.getId()))
                bankAccount.getIncomingTransactionList().add(transaction.getId());
        }
        else {
            if(!bankAccount.getOutGoingTransactionList().contains(transaction.getId()))
                bankAccount.getOutGoingTransactionList().add(transaction.getId());
        }

        try {
            bankAccountRepository.updateBankAccount(bankAccount);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Dashboard prepareUserDashboard(BigInteger userId) throws Exception {
        Dashboard dashboard = new Dashboard();
        UserService userService = new UserService();
        dashboard.setAccounts(this.getBankAccountsForUser(userId));
        dashboard.setUserName(userService.getUserName(userId));
        dashboard.setNumberOfAccounts(dashboard.getAccounts().size());
        for(BankAccount ba : dashboard.getAccounts()){
            if(dashboard.getQuotaPerCurrency().containsKey(ba.getCurrency())){
                // get obj at key, add new quota to total sum
                dashboard.getQuotaPerCurrency().replace(
                    ba.getCurrency(),
                    dashboard.getQuotaPerCurrency().get(ba.getCurrency()) + ba.getAvailableQuota()
                );
            }else
                dashboard.getQuotaPerCurrency().put(
                        ba.getCurrency(),
                        ba.getAvailableQuota()
                );
        }
        return dashboard;
    }

}
