package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.models.*;
import com.isa.Bankersi2k24.repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DataGenerator {
    private final UserInfoRepository userInfoRepository;
    private Random random;
    private final BankAccountService bankAccountService;
    private final UserService userService;
    private final TransactionService transactionService;

    public DataGenerator(BankAccountService bankAccountService, UserService userService, TransactionService transactionService, UserInfoRepository userInfoRepository) {
        this.bankAccountService = bankAccountService;
        this.userService = userService;
        this.transactionService = transactionService;
        this.userInfoRepository = userInfoRepository;
    }

    @PostConstruct
    public void init(){
        this.random = new Random();
        int howMany = 6;
        List<BankAccount> bankAccountList = new ArrayList<>();
        List<User> users = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();

        int phase = 0;

        switch(phase) {
            case 0:
                users = this.generateUsers(howMany);
                UserInfo userInfo;
                for (User user : users) {
                    userService.saveNewUser(user);
                    userInfo = UserInfo.builder()
                            .username(user.getLogin())
                            .password(user.getPassword())
                            .roles("USER")
                            .build();
                    userInfoRepository.save(userInfo);
                }
                UserInfo u1 = UserInfo.builder()
                        .username("user")
                        .password(new BCryptPasswordEncoder().encode("user"))
                        .roles("USER")
                        .build();
                UserInfo u2 = UserInfo.builder()
                        .username("admin")
                        .password(new BCryptPasswordEncoder().encode("admin"))
                        .roles("ADMIN")
                        .build();
                userInfoRepository.save(u1);
                userInfoRepository.save(u2);

                User u = User.builder()
                        .login(u2.getUsername())
                        .password(u2.getPassword())
                        .build();
                userService.saveNewUser(u);

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
            case 666:
                List<Transaction> transactions1 = this.transactionService.getAllTransactionsForBankAccount(
                        this.bankAccountService.getAllBankAccounts().get(1)
                );
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
                    .senderBankAccountNumber(ba1.getBankAccountNumber())
                    .destinationBankAccountNumber(ba2.getBankAccountNumber())
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
            String lastName = lastNames[i].toString(); //lastNames[random.nextInt(lastNames.length)].toString();
            User u = User.builder()
                    .name(name)
                    .lastName(lastName)
                    .creationDate(new Date())
                    .login(lastName.toLowerCase() + name.toLowerCase().charAt(0))
//                    .password(new BCryptPasswordEncoder().encode("#" + name.toLowerCase() + "!"))
                    .password(new BCryptPasswordEncoder().encode(lastName.toLowerCase() + name.toLowerCase().charAt(0)))
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
