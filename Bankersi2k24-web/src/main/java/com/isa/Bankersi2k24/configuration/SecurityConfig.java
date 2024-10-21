package com.isa.Bankersi2k24.configuration;

import com.isa.Bankersi2k24.components.CustomAuthenticationProvider;
import com.isa.Bankersi2k24.services.UserDetailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.RequestContextFilter;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    /**
     *  old way taught in bootcamp is deprecated,
     *  see: <a href="https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter">...</a>
     */
    private final UserDetailServiceImpl userDetailService;
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailService);
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, RequestContextFilter requestContextFilter) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .requestMatchers("/web/**").authenticated()
                .requestMatchers("/api**/**").hasRole("API")
                                .anyRequest().authenticated()
                )

//                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
//                .authenticationProvider(customAuthenticationProvider)

        .formLogin( (form) -> form
                .loginPage("/login")
                .loginProcessingUrl("/auth")
                .defaultSuccessUrl("/web/dashboard")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
