package pl.isa.view;

import pl.isa.dataAccess.Connector;
import pl.isa.model.User;

import java.util.Scanner;

//jira task: https://jira.is-academy.pl/browse/JJDZR14BA-3
public class WelcomeScreen {



    public void loginScreen() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your login..");
        String login = scanner.next();
        System.out.println("Enter your password..");
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
        String name = "";
        String lastName = "";
        String pesel = "";
        do {
            System.out.println("Enter your name...");
            name = scanner.next();
            user.setName(name);
        } while (specialCharacters(name) || badNumbers(name));
        do {
            System.out.println("Enter your last name...");
            lastName = scanner.next();
            user.setLastName(lastName);
        } while (specialCharacters(lastName) || badNumbers(lastName));
        do {
            System.out.println("Enter your pesel...");
            pesel = scanner.next();
            user.setPesel(pesel);
        } while (specialCharacters(pesel)|| !checkPeselLenght(pesel)|| badLetters(pesel));

        System.out.println("Enter login..");
        String login = scanner.next();
        user.setLogin(login);

        System.out.println("Enter password..");
        String password = scanner.next();
        user.setPassword(password);
        Connector connector = new Connector();
        connector.save(user);

    } // Zarejestuj -> Imie, nazwisko, login, hasło. Tutaj mam problem z IF.
    private boolean checkPeselLenght(String pesel) {
        if (pesel.length() != 11) {
            System.out.println("Invalid pesel, pesel must have 11 digits");
            return false;
        }
        else return true;

    }

    public boolean specialCharacters(String special) {
        String specialCharacters = "!@#$%^&*()_+-=[]{}|;':,.<>?";
        for (char s : special.toCharArray()) {
            if (specialCharacters.contains(String.valueOf(s))) {
                System.out.println("No special characters.");
                return true;
            }
        }
        return false;
    } // Walidacja danych logowania -> Unikanie znaków specjalnych dla imienia i nazwisko,

    public boolean badNumbers(String numbers) {
        for (char n : numbers.toCharArray()) {
            if (Character.isDigit(n)) {
                System.out.println("No digits.");
                return true;
            }
        }
        return false;
    }
    public boolean badLetters(String letters) {
        for (char n : letters.toCharArray()) {
            if (!Character.isDigit(n)) {
                System.out.println("No letters.");
                return true;
            }
        }
        return false;
    }

    public boolean checkloginScreen(String login, String password) {
        return false;


    }
}


