package com.isa.Bankersi2k24.repository;

import com.isa.Bankersi2k24.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    @Query("select u from UserInfo u where u.username = ?1")
    Optional<UserInfo> findByUsername(String username);
}
