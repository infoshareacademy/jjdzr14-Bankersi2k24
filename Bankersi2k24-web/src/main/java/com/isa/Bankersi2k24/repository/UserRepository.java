package com.isa.Bankersi2k24.repository;

import com.isa.Bankersi2k24.DAO.FileName;
import com.isa.Bankersi2k24.DAO.Serializable;
import com.isa.Bankersi2k24.models.User;

import java.util.List;
import java.util.function.Predicate;

public class UserRepository extends Serializable<User> {
    private List<User> users;
//    private static UserRepository INSTANCE = null;

//    public static UserRepository UserRepository(){
//        if(INSTANCE == null) {
//            INSTANCE = new UserRepository();
//        }
//        return INSTANCE;
//    }
    public UserRepository() {
        super(FileName.USER, User.class);
        this.users = fetchAllObjects();
    }

    public List<User> fetchAllUsers() {
        return this.users;
    }

    public User getUser(int id){
        return this.users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public void saveNewUser(User user){
        this.save(user);
        this.invalidateUserList();
    }

    public void updateUser(User User){
        this.users.set(
                this.users.indexOf(User),
                User
        );
        this.save();
        this.invalidateUserList();
    }

    public boolean queryUsers(Predicate<User> predicate){
        return !this.users.stream().noneMatch(predicate);
    }

    public boolean deleteUser(Integer id){
        return this.users.removeIf(u -> u.getId().equals(id));
    }

    private void invalidateUserList(){
        this.users = fetchAllObjects();
    }
}
