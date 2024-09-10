package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.models.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DataGenerator {
    private Random random;
    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;

    public DataGenerator() {
//
//        List<Transaction> transactions = this.generateRandomTransactions(howMany, bankAccountList);
//        try{
//            for (Transaction transaction : transactions) {
//                transactionService.saveNewTransaction(transaction);
//                transactionService.triggerTransaction(transaction);
//                transactions.forEach(transactionService::updateTransaction);
//            }
//        }catch (Exception e){
//            // not enough quota on account or currency
//        }
    }

    @PostConstruct
    public void generate(){
        this.random = new Random();
        int howMany = 13;
        List<BankAccount> bankAccountList = new ArrayList<>();
        List<User> users = new ArrayList<>();

        int phase = 10;

        switch(phase) {
            case 0:
                users = this.generateUsers(howMany);
                for (User user : users) {
                    userService.saveNewUser(user);
                }
                break;
            case 1:
                users = this.userService.getAllUsers();
                bankAccountList = this.generateBankAccountsForUsers(users);
                bankAccountList.forEach(bankAccountService::saveBankAccount);
                break;
            case 10:
                bankAccountList = bankAccountService.getAllBankAccounts();
                users = userService.getAllUsers();
                break;
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
            transaction.setSenderBankAccount(ba1);
            transaction.setDestinationBankAccount(ba2);

            transaction.setCurrency(Currencies.EUR);
            ret.add(transaction);
        }
        return ret;
    }

    private List<BankAccount> generateBankAccountsForUsers(List<User> users){
        List<BankAccount> bankAccounts = new ArrayList<>(users.size());
        Collections.shuffle(users);
        for(User user : users){
            BankAccount bankAccount = bankAccountService.createNewBankAccountForUser(
                                                    user,
                                                    BigDecimal.valueOf(new Random().nextInt(0,10000)),
                                                    Currencies.EUR);
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
                    .bankAccounts(Collections.emptyList())
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
