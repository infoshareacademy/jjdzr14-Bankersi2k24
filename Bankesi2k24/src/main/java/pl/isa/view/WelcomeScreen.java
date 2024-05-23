package pl.isa.view;

import pl.isa.dataAccess.Connector;
import pl.isa.dataAccess.FileNames;
import pl.isa.dataAccess.ObjectToJson;
import pl.isa.model.User;

import java.util.Objects;
import java.util.Scanner;
import java.util.function.Predicate;

//jira task: https://jira.is-academy.pl/browse/JJDZR14BA-3
public class WelcomeScreen {
    public int showWelcomeScreen(){
        Scanner s = new Scanner(System.in);
        String input = "";
        while(true){
            System.out.println("Hello, welcome in Bankersi2k24, what would you like to do?\n" +
                    "1: login to your account\n"+
                    "2: register as a new user\n"
            );
            input = s.nextLine();
            try{
                Integer i = Integer.parseInt(input);
                if(i == 1) return 1;
                if(i == 2) return 2;
            } catch (NumberFormatException e) {
                System.out.println("please provide a valid choice!");
            }
        }
    }


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

    private String askForInput(String prompt, Predicate<String> predicate){
        Scanner scanner = new Scanner(System.in);
        String input = "";
        do{
            System.out.println(prompt + "\n");
            input = scanner.next();
        }while(predicate.test(input));
        return input;
    }

    public void registrationScreen() {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        String name = "", lastName = "", login = "", password="", email ="";
        Predicate<String> isAllowedName = s -> {
            return (specialCharacters(s) || badNumbers(s));
        };

        name = this.askForInput("Enter your name ...", isAllowedName);
        lastName = this.askForInput("Enter your last name ... ", isAllowedName);
        email = this.askForInput("Enter email address: ", s->!User.verifyEmail(s));
        login = this.askForInput("Provide your login: ", this::specialCharacters);
        password = this.askForInput("Provide password: ", s->false);
        String finalPassword = password;
        password = this.askForInput("repeat password: ", s-> !Objects.equals(s, finalPassword));

        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);

        Connector connector = new Connector(FileNames.USER.toString());
        ObjectToJson objectToJson = new ObjectToJson<User>();
        connector.save(objectToJson.serialize(user));

    } // Zarejestuj -> Imie, nazwisko, login, hasło. Tutaj mam problem z IF.


    private boolean specialCharacters(String special) {
        String specialCharacters = "!@#$%^&*()_+-=[]{}|;':,.<>?";
        for (char s : special.toCharArray()) {
            if (specialCharacters.contains(String.valueOf(s))) {
                System.out.println("No special characters.");
                return true;
            }
        }
        return false;
    } // Walidacja danych logowania -> Unikanie znaków specjalnych dla imienia i nazwisko,

    private boolean badNumbers(String numbers) {
        for (char n : numbers.toCharArray()) {
            if (Character.isDigit(n)) {
                System.out.println("No digits.");
                return true;
            }
        }
        return false;
    }

    public boolean checkloginScreen(String login, String password) {
        return false;


    }
}


