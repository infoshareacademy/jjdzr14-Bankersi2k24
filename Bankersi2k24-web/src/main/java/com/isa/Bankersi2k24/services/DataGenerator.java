package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.models.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DataGenerator {
    private final Random random;
    private final BankAccountService bankAccountService;


    @PostConstruct
    public void init(){

        this.random = new Random();
        this.bankAccountService = bankAccountService;

        int howMany = 13;
        List<BankAccount> bankAccountList = new ArrayList<>();
        List<User> users = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();

        int phase = 0;

        switch(phase) {
            case 0:
                users = this.generateUsers(howMany);
                for (User user : users) {
                    userService.saveNewUser(user);
                }
//                break;
            case 1:
                users = this.userService.getAllUsers();
                bankAccountList = this.generateBankAccountsForUsers(users);
                bankAccountList.forEach(bankAccountService::saveBankAccount);
//                break;
            case 2:
                transactions = this.generateRandomTransactions(howMany, bankAccountService.getAllBankAccounts());
                transactions.forEach(transactionService::saveNewTransaction);
//                break;
            case 10:
                bankAccountList = bankAccountService.getAllBankAccounts();
                users = userService.getAllUsers();
                transactions = transactionService.getAllTransactions();
                break;
        }
    }

    private List<Transaction> generateRandomTransactions(int howMany, List<BankAccount> bankAccountList){
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            BankAccount ba1 = bankAccountList.get(this.random.nextInt(0, bankAccountList.size()));
            Collections.shuffle(bankAccountList);
            BankAccount ba2 = bankAccountList.stream()
                        .filter(ba -> !(ba1.equals(ba)))
                        .findFirst()
                        .get();
            Transaction transaction = Transaction.builder()
                    .quota(BigDecimal.valueOf(this.random.nextInt(0,350)))
                    .transactionDate(LocalDateTime.now())
                    .transactionTitle("transactionTitle-"+ this.random.nextInt(0, 1000))
                    .currency(Currencies.EUR)
                    .isComplete(this.random.nextBoolean())
                    .senderBankAccount(ba1)
                    .destinationBankAccount(ba2)
                    .build();
            if(transaction.isComplete())
                transaction.setTrackingNumber();
            transactions.add(transaction);
        }
        return transactions;
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
