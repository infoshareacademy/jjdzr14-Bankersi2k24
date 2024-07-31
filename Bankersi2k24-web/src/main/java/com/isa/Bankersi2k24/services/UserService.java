package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.DAO.FileService;
import com.isa.Bankersi2k24.DAO.FileName;
import com.isa.Bankersi2k24.DAO.Serializable;
import com.isa.Bankersi2k24.models.User;
import com.isa.Bankersi2k24.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PESEL_REGEX =
            Pattern.compile("\\d{11}", Pattern.CASE_INSENSITIVE);
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public User saveNewUser(User user){
        if(user.getId() == null)
            user.setId(this.findNewIdForUser());
        this.userRepository.saveNewUser(user);
        return user;
    }

    private BigInteger findNewIdForUser(){
        if(this.userRepository.fetchAllUsers().isEmpty())
            return BigInteger.ONE;
        else {
            return this.userRepository.getNewId();
        }
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

    private List<User> fetchAllUsers(){
        return this.userRepository.fetchAllUsers();
    }

}
