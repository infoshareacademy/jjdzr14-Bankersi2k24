package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.dataAccess.FileService;
import com.isa.Bankersi2k24.dataAccess.FileName;
import com.isa.Bankersi2k24.dataAccess.Serializable;
import com.isa.Bankersi2k24.models.User;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PESEL_REGEX =
            Pattern.compile("\\d{11}", Pattern.CASE_INSENSITIVE);

    public static User findUserByLogin(String login){
        List<User> users = fetchAllUsers();

        for(User u : users){
            if(login.equals(u.getLogin())){
                return u;
            }
        }
        return null;
    }
    public static User findUserByPesel(String pesel){
        FileService fileService = new FileService(FileName.USER.toString());
        Serializable<User> objectToJson = new Serializable<>(FileName.USER, User.class);

        List<User> users = objectToJson.deserialize(fileService.read(), User.class);

        for(User user : users){
            if(pesel.equals(user.getPesel())){
                return user;
            }
        }
        return null;
    }

    public static boolean verifyEmail(String email){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.matches();
    }
    public static boolean verifyPesel(String pesel){
        Matcher matcher = VALID_PESEL_REGEX.matcher(pesel);
        return matcher.matches();
    }

    private static boolean verifyLogin(User user, String login){
        return Objects.equals(user.getLogin(), login);
    }

    private static boolean verifyPassword(User user, String password){
        return Objects.equals(user.getPassword(), password);
    }

    public static boolean verifyCredentials(User user, String login, String password){
        if(verifyLogin(user, login)) return verifyPassword(user, password);
        else return false;
    }

    private static List<User> fetchAllUsers(){
        FileService fileService = new FileService(FileName.USER.toString());
        Serializable<User> objectToJson = new Serializable<>(FileName.USER, User.class);

        return objectToJson.deserialize(fileService.read(), User.class);
    }

    public static User saveNewUser(User user){
        user.setId(User.generateNewId(User.class));
        user.save();

        return user;
    }

}
