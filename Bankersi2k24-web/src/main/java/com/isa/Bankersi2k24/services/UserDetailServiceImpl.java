package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.models.UserDetailModel;
import com.isa.Bankersi2k24.models.UserInfo;
import com.isa.Bankersi2k24.repository.UserInfoRepository;
import com.isa.Bankersi2k24.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepo;
    private UserInfoRepository userInfoRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> user = userInfoRepo.findByUsername(username);
        return user.map(UserDetailModel::new).orElseThrow( () -> new UsernameNotFoundException(username));

    }
}
