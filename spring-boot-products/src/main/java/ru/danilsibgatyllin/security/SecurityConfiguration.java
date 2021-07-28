package ru.danilsibgatyllin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    public void authConfig(AuthenticationManagerBuilder auth,
                           PasswordEncoder passwordEncoder,
                           UserAuthService userAuthService) throws Exception {
        //in memory provaider
        auth.inMemoryAuthentication()
                .withUser("mem_user")
                .password(passwordEncoder.encode("mem_user")) //шифрование пароля в оперативной памяти
                .roles("ADMIN")
                .and()
                .withUser("mem_gest")
                .password(passwordEncoder.encode("mem_gest"))
                .roles("GUEST");

        auth.userDetailsService(userAuthService); // auth for users in list
    }
}
