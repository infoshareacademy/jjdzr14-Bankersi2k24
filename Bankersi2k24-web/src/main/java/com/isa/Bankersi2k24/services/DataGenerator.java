package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataGenerator {

    private Random random;

    public DataGenerator() {
        this.random = new Random();
        UserService userService = new UserService();

        List<User> users = this.generateUsers(10);
        users.forEach(userService::saveNewUser);
        users.forEach(this::addBankAccountToUsers);
    }

    private void addBankAccountToUsers(User user){
        BankAccountService bankAccountService = new BankAccountService();
        BankAccount bankAccount = bankAccountService.createNewBankAccount(user.getId());
        bankAccount.setAvailableQuota(new Random().nextInt(0,10000));
        bankAccountService.saveBankAccount(bankAccount);
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
        Mägi,
        Kask,
        Kukk,
        Rebane,
        Ilves,
        Pärn,
        Koppel
    }

}
