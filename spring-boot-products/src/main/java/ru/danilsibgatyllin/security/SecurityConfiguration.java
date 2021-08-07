package ru.danilsibgatyllin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    public void authConfig(AuthenticationManagerBuilder auth,
                           PasswordEncoder passwordEncoder,
                           UserAuthService userAuthService) throws Exception {
        //in memory provaider
        auth.inMemoryAuthentication()
                .withUser("mem_user")
                .password(passwordEncoder.encode("mem_user")) //шифрование пароля в оперативной памяти
                .roles("SUPER_ADMIN")
                .and()
                .withUser("mem_gest")
                .password(passwordEncoder.encode("mem_gest"))
                .roles("GUEST");

        auth.userDetailsService(userAuthService); // auth for users in list
    }

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic()// включаем базовую авторизацию ниже выдаем джесон при не удачной авторизации
                    .authenticationEntryPoint((reg,resp,exception) ->{
                        resp.setContentType("aplication/json");
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().println("{\"error\":\""+exception.getMessage()+"\"}");
                    })
                    .and()
                    .csrf().disable() //выключаем стандартное поддержание авторизации в сессии
                    .sessionManagement() //добавляем кастомный механизм поддержания сессии
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // каждый вызов будет требовать авторизации
            ;
        }
    }

    @Configuration
    @Order(2)
    public static class UiWebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/**/*.css", "/**/*.js").permitAll()
                    .antMatchers("/product/**").permitAll()
                    .antMatchers("/user/new").permitAll()
                    .antMatchers("/user/**").hasAnyRole("ADMIN","SUPER_ADMIN")
                    .antMatchers("/access_denied").authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login_processing")
                    .defaultSuccessUrl("/product")
                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/access_denied");
        }
    }
}
