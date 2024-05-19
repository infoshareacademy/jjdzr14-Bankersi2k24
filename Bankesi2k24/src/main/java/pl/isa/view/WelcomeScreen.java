package pl.isa.view;

import pl.isa.model.User;

import java.util.Scanner;

//jira task: https://jira.is-academy.pl/browse/JJDZR14BA-3
public class WelcomeScreen {


    public void loginScreen() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your login..");                               //Ryszard
        String login = scanner.next();
        System.out.println("Enter your password..");                             //Ryszard
        String password = scanner.next();

        if (checkloginScreen(login, password)) {
            System.out.println("Login success");
        } else {
            System.out.println("Invalid login or password");
            return;
        }
        System.out.println("Welcome!!!  " + login);
    } // Zaloguj -> podaj login, haslo z zawartą metodą checkloginScreen

    public void registrationScreen() {
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        System.out.println("Enter your name...");
        String name = scanner.next();
        user.setName(name);

        System.out.println("Enter your last name...");
//        setLastName(scanner.next());
        String lastName = scanner.next();
        user.setLastName(lastName);

//        if (getName().length() == 0 || getLastName().length() == 0) {
//            System.out.println("Why empty phrases? Please, try again");
//        } NIE WIEM DLACZEGO ALE TO NIE DZIAŁA;/

        if (specialCharacters(name) || badNumbers(name) || specialCharacters(lastName) || badNumbers(lastName)) {
            System.out.println("Invalid input. Please enter only letters.");
            return;                                                                        //Ryszard po zmianach

//        if (specialCharacters(getName()) || badNumbers(getName()) || specialCharacters(getLastName()) || badNumbers(getLastName())) {
//            System.out.println("Invalid input. Please enter only letters.");
//            return; //Ryszard
        }
        System.out.println("Enter your new login...");
        String login = scanner.next();
        user.setLogin(login);
//        setRegLogin(scanner.next());                                       // to bylo Jacka
        String regLogin = scanner.next();                                    //Ryszard
        System.out.println("Enter your new password...");
        String password = scanner.next();
        user.setPassword(password);

//        setRegPassword(scanner.next());                                 // to bylo Jacka
//        System.out.println("New user:" + getName().toUpperCase() + " " + getLastName().toUpperCase());  to bylo Jacka
        System.out.println("New user:" + name.toUpperCase() + " " + lastName.toUpperCase()); // Rysard
    } // Zarejestuj -> Imie, nazwisko, login, hasło. Tutaj mam problem z IF.

    public boolean specialCharacters(String special) {
        String specialCharacters = "!@#$%^&*()_+-=[]{}|;':,.<>?";
        for (char s : special.toCharArray()) {
            if (specialCharacters.contains(String.valueOf(s))) {
                return true;
            }
        }
        return false;
    } // Walidacja danych logowania -> Unikanie znaków specjalnych dla imienia i nazwisko,

    public boolean badNumbers(String numbers) {
        for (char n : numbers.toCharArray()) {
            if (Character.isDigit(n)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkloginScreen(String login, String password) {       //Ryszard

        if (login.equals(regLogin()) && password.equals(getPassword())) {
            return true;
        } else {
            return false;
        }
    }
}


