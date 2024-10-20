package com.isa.Bankersi2k24.components;

import com.isa.Bankersi2k24.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try{
            UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                    userDetails.getPassword(),
                    userDetails.getAuthorities());
        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
