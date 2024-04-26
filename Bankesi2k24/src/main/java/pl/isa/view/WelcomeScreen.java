package pl.isa.view;

import java.util.Scanner;

//jira task: https://jira.is-academy.pl/browse/JJDZR14BA-3
public class WelcomeScreen {
    private String login;
    private String password;
    private String name;
    private String lastName;
    private String regLogin;
    private String regPassword;

    public String getRegLogin() {
        return regLogin;
    }

    public void setRegLogin(String regLogin) {
        this.regLogin = regLogin;
    }

    public String getRegPassword() {
        return regPassword;
    }

    public void setRegPassword(String regPassword) {
        this.regPassword = regPassword;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void loginScreen() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your login..");
        setLogin(scanner.next());
        System.out.println("Enter your password..");
        setPassword(scanner.next());
        if (checkloginScreen(getRegLogin(), getRegPassword())) {
            System.out.println("Login success");
        } else {
            System.out.println("Invalid login or password");
            return;
        }
        System.out.println("Welcome!!!  " + getLogin());
    } // Zaloguj -> podaj login, haslo z zawartą metodą checkloginScreen

    public void registrationScreen() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name...");
        setName(scanner.next());
        System.out.println("Enter your last name...");
        setLastName(scanner.next());
//        if (getName().length() == 0 || getLastName().length() == 0) {
//            System.out.println("Why empty phrases? Please, try again");
//        } NIE WIEM DLACZEGO ALE TO NIE DZIAŁA;/
        if (specialCharacters(getName()) || badNumbers(getName()) || specialCharacters(getLastName()) || badNumbers(getLastName())) {
            System.out.println("Invalid input. Please enter only letters.");
            return;
        }
        System.out.println("Enter your new login...");
        setRegLogin(scanner.next());
        System.out.println("Enter your new password...");
        setRegPassword(scanner.next());
        System.out.println("New user:" + getName().toUpperCase() + " " + getLastName().toUpperCase());
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
    } // Walidacja danych logowania -> Unikanie cyfr dla imienia i nazwisko, imię i nazwisko nie może być puste

    public boolean checkloginScreen(String regLogin, String regPassword) {
        if (regLogin.equals(getLogin()) && regPassword.equals(getPassword())) {
            return true;
        } else {
            return false;
        }
    } // metoda porównuje gety loginu i hasła przy rejestracji z logowaniem

}
