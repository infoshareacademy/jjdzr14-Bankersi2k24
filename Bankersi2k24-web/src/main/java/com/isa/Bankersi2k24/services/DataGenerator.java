package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.models.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataGenerator {

    private final Random random;
    private final BankAccountService bankAccountService;


    public DataGenerator(BankAccountService bankAccountService, TransacrionService transacrionService, UserService userService) {
        this.random = new Random();
        this.bankAccountService = bankAccountService;

        int howMany = 13;

        List<User> users = this.generateUsers(howMany);
        users.forEach(userService::saveNewUser);

        List<BankAccount> bankAccountList = this.generateBankAccountsForUsers(users);
        bankAccountList.forEach(bankAccountService::saveBankAccount);

        List<Transaction> transactions = this.generateRandomTransactions(howMany, bankAccountList);
        try{
            for (Transaction transaction : transactions) {
                transactionService.saveNewTransaction(transaction);
                transactionService.triggerTransaction(transaction);
                transactions.forEach(transactionService::updateTransaction);
            }
        }catch (Exception e){
            // not enough quota on account or currency
        }
    }

    private List<Transaction> generateRandomTransactions(int howMany, List<BankAccount> bankAccountList){
        List<Transaction> ret = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            BankAccount ba1 = bankAccountList.get(this.random.nextInt(0, bankAccountList.size()));
            Collections.shuffle(bankAccountList);
            BankAccount ba2 = bankAccountList.stream()
                        .filter(ba -> !(ba1.equals(ba)))
                        .findFirst()
                        .get();
            Transaction transaction = new Transaction();
            transaction.setTransactionTitle("transactionTitle-"+ this.random.nextInt(0, 1000));
            transaction.setQuota(BigDecimal.valueOf(this.random.nextInt(0,350)));

            transaction.setSenderAccountNumber(ba1.getBankAccountNumber());
            transaction.setDestinationAccountNumber(ba2.getBankAccountNumber());
            transaction.setCurrency(Currencies.EUR);
            ret.add(transaction);
        }
        return ret;
    }

    private List<BankAccount> generateBankAccountsForUsers(List<User> users){
        List<BankAccount> bankAccounts = new ArrayList<>(users.size());
        Collections.shuffle(users);
        for(User user : users){
            BankAccount bankAccount = bankAccountService.createNewBankAccount(user.getId());
            bankAccount.setAvailableQuota(BigDecimal.valueOf(new Random().nextInt(0,10000)));
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
            String name = names[random.nextInt(names.length)].toString();
            String lastName = lastNames[random.nextInt(lastNames.length)].toString();
            User u = User.builder()
                    .name(name)
                    .lastName(lastName)
                    .creationDate(new Date())
                    .login(lastName.toLowerCase() + name.toLowerCase().charAt(0))
                    .password("#" + name.toLowerCase() + "!")
                    .email(name.toLowerCase() + "." + lastName.toLowerCase() + "@bankersi2k24.biz")
                    .taxId(random.ints(9, 0,10)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining()))
                    .build();
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
