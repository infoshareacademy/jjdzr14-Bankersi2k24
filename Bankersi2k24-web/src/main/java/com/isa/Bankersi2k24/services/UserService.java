package com.isa.Bankersi2k24.services;

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
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveNewUser(User user){
        userRepository.save(user);
    }

    private BigInteger findNewIdForUser(){
//        if(this.userRepository.fetchAllUsers().isEmpty())
//            return BigInteger.ONE;
//        else {
//            return this.userRepository.getNewId();
//        }
        return null;
    }

    public User findUserByLogin(String login){
        List<User> users = fetchAllUsers();

        for(User u : users){
            if(login.equals(u.getLogin())){
                return u;
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

    public boolean verifyCredentials(User user, String login, String password){
        if(verifyLogin(user, login)) return verifyPassword(user, password);
        else return false;
    }

    private List<User> fetchAllUsers(){
        return null;
//        return this.userRepository.fetchAllUsers();
    }

    public String getUserName(BigInteger id) throws Exception {
        try{
            return this.fetchAllUsers().stream().filter(u -> u.getId().equals(id))
                    .findFirst()
                    .orElseThrow()
                    .getName();
        }catch (Exception e){
            throw new Exception(String.format("User with id: %d does not exist", id));
        }
    }
    public User getUserById(BigInteger id){
        try{
            return this.fetchAllUsers().stream().filter(u -> u.getId().equals(id))
                    .findFirst().get();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public boolean loginUser(String login, String password) throws Exception {
        User user = this.findUserByLogin(login);
        if(user != null){
            return this.verifyCredentials(user, login, password);
        }else{
            throw new Exception("Login failed - check your credentials");
        }
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}

