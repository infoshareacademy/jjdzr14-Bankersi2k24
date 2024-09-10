package com.isa.Bankersi2k24.repository;

import com.isa.Bankersi2k24.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepository extends JpaRepository<User, BigInteger> {
    @Query("select u from User u where u.login like ?1")
    User findUserByLogin(String login);

}
