package pl.isa.services;

import pl.isa.dataAccess.Connector;
import pl.isa.dataAccess.FileNames;
import pl.isa.dataAccess.ObjectToJson;
import pl.isa.model.BankAccount;
import pl.isa.model.Currencies;
import pl.isa.model.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static User findUser(String login){
        List<User> users = fetchAllUsers();

        for(User u : users){
            if(login.equals(u.getLogin())){
                return u;
            }
        }
        return null;
    }
    public void createFakeBankAccount(User user, int quota, Currencies curr){
        user.setBankAccount(BankAccount.createFakeBankAccount(quota, curr));
    }

    public static boolean verifyEmail(String email){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.matches();
    }

    private boolean verifyLogin(User user, String login){
        return Objects.equals(user.getLogin(), login);
    }

    private boolean verifyPassword(User user, String password){
        return Objects.equals(user.getPassword(), password);
    }

    public boolean verifyCredentials(User user, String login, String password){
        if(this.verifyLogin(user, login)) return this.verifyPassword(user, password);
        else return false;

    }

    public static void saveToDb(User user){
        ObjectToJson<User> objectToJson = new ObjectToJson<User>(FileNames.USER, User.class);
        objectToJson.save(user, User.class);

    }

    private static List<User> fetchAllUsers(){
        Connector connector = new Connector(FileNames.USER.toString());
        ObjectToJson<User> objectToJson = new ObjectToJson<>(FileNames.USER, User.class);

        return objectToJson.deserialize(connector.read(), User.class);
    }

    public static Integer generateNewId(){
        List<User> users = fetchAllUsers();
        Comparator<User> comparator = Comparator.comparing(User::getId);
        return (users.isEmpty()) ? 0 : Collections.max(users, comparator).getId()+1;
    }

    public static User createNewUser(String name, String lastName, String email, String login,String password){
        User newUser = new User();

        newUser.setName(name);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setId(UserService.generateNewId());

        UserService.saveToDb(newUser);

        return newUser;
    }

}
