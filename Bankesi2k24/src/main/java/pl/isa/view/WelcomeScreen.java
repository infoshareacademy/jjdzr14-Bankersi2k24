package pl.isa.view;

import pl.isa.dataAccess.Connector;
import pl.isa.dataAccess.FileNames;
import pl.isa.dataAccess.ObjectToJson;
import pl.isa.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Predicate;

//jira task: https://jira.is-academy.pl/browse/JJDZR14BA-3
public class WelcomeScreen {
    public int action = 0;

    public void showWelcomeScreen(){
        Scanner s = new Scanner(System.in);
        String input = "";
        while(true){
            System.out.println("Hello, welcome in Bankersi2k24, what would you like to do?\n" +
                    "1: login to your account\n"+
                    "2: register as a new user\n"+
                    "-1: to exit"
            );
            input = s.nextLine();
            try{
                Integer i = Integer.parseInt(input);
                if(i == 1) this.action = 1;
                if(i == 2) this.action = 2;
                if(i == -1) this.action = -1;
                break;
            } catch (NumberFormatException e) {
                System.out.println("please provide a valid choice!");
            }
        }
    }


    public String[] loginScreen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your login..");
        String login = scanner.next();
        System.out.println("Enter your password..");
        String password = scanner.next();

        return new String[] {login, password};

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

        name = this.askForInput("Enter your name ... (only letters)", isAllowedName);
        lastName = this.askForInput("Enter your last name ... (only letters)", isAllowedName);
        email = this.askForInput("Enter email address: (must use proper email format)", s->!User.verifyEmail(s));
        login = this.askForInput("Provide your login: (no special characters allowed)", this::specialCharacters);
        while(User.findUser(login) != null){
            login = this.askForInput("Such login already exists, provide a different login: ", this::specialCharacters);
        }
        password = this.askForInput("Provide password: ", s->false);
        String finalPassword = password;
        password = this.askForInput("repeat password: ", s-> !Objects.equals(s, finalPassword));

        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);

        ObjectToJson<User> objectToJson = new ObjectToJson<User>(FileNames.USER, User.class);
        objectToJson.save(user, User.class);


    } // Zarejestuj -> Imie, nazwisko, login, hasło. Tutaj mam problem z IF.


    private boolean specialCharacters(String special) {
        String specialCharacters = "!@#$%^&*()_+-=[]{}|;':,.<>?";
        for (char s : special.toCharArray()) {
            if (specialCharacters.contains(String.valueOf(s))) {
                System.out.println("special characters like: "+specialCharacters+" are not allowed");
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
}


