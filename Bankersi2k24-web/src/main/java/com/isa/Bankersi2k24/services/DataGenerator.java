package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.models.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataGenerator {

    private Random random;

    public DataGenerator(int howMany) {
        this.random = new Random();
        UserService userService = new UserService();
        TransacrionService transacrionService = new TransacrionService();
        BankAccountService bankAccountService = new BankAccountService();

        List<User> users = this.generateUsers(howMany);
        users.forEach(userService::saveNewUser);

        List<BankAccount> bankAccountList = this.generateBankAccountsForUsers(users);
        bankAccountList.forEach(bankAccountService::saveBankAccount);

        List<Transaction> transactions = this.generateRandomTranssactions(howMany, bankAccountList);
        transactions.forEach(transacrionService::triggerTransaction);
        transactions.forEach(transacrionService::saveNewTransaction);
    }

    private List<Transaction> generateRandomTranssactions(int howMany, List<BankAccount> bankAccountList){
        List<Transaction> ret = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            BankAccount ba1 = bankAccountList.get(this.random.nextInt(0, bankAccountList.size()));
            BankAccount ba2 = bankAccountList.stream()
                        .filter(ba -> !(ba1.equals(bankAccountList.indexOf(ba1))))
                        .findFirst()
                        .get();
            Transaction transaction = new Transaction();
            transaction.setTransactionTitle("transactionTitle-"+String.valueOf(this.random.nextInt(0,1000)));
            transaction.setQuota(this.random.nextInt(0,350));
            transaction.setSenderAccountNumber(ba1.getBankAccountNumber());
            transaction.setDestinationAccountNumber(ba2.getBankAccountNumber());
            ret.add(transaction);
        }
        return ret;
    }

    private List<BankAccount> generateBankAccountsForUsers(List<User> users){
        List<BankAccount> bankAccounts = new ArrayList<>(users.size());
        BankAccountService bankAccountService = new BankAccountService();
        Collections.shuffle(users);
        for(User user : users){
            BankAccount bankAccount = bankAccountService.createNewBankAccount(user.getId());
            bankAccount.setAvailableQuota(new Random().nextInt(0,10000));
            bankAccount.setCurrency(Currencies.EUR);
            bankAccounts.add(bankAccount);
        }
        return bankAccounts;
    }

    private List<User> generateUsers(int howMany){
        List<User> users = new ArrayList<>(howMany);

        ExampleNames[] names = ExampleNames.values();
        ExampleSurnames[] lastNames = ExampleSurnames.values();

        for (int i = 0; i < howMany; i++) {
            User u =new User();
            String name = names[random.nextInt(names.length)].toString();
            String lastName = lastNames[random.nextInt(lastNames.length)].toString();

            u.setName(name);
            u.setLastName(lastName);
            u.setLogin(lastName.toLowerCase() + name.toLowerCase().charAt(0));
            u.setPassword("#" + name.toLowerCase() + "!");
            u.setEmail(name.toLowerCase() + "." + lastName.toLowerCase() + "@bankersi2k24.biz");
            u.setPesel(random.ints(9, 0,10)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining()));
            users.add(u);
        }
        return users;
    }


    private enum ExampleNames{
        Mark,
        Hugo,
        Robin,
        Miron,
        Oliver,
        Sebastian,
        Rasmus,
        David,
        Martin,
        Jasper
    }

    private enum ExampleSurnames{
        Tamm,
        Saar,
        Sepp,
        Magi,
        Kask,
        Kukk,
        Rebane,
        Ilves,
        Parn,
        Koppel
    }

}
