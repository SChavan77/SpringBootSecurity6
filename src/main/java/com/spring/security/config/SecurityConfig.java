package com.spring.security.config;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

                    httpSecurity
                            .csrf(csrf->csrf.disable()) //for modifying, csrf is disabled
                            .authorizeHttpRequests(
                                request -> request
                                        .requestMatchers("register","login").permitAll()
                                        .anyRequest().authenticated()) //authenticate all the requests
                            //.formLogin(Customizer.withDefaults()) //Authentication page comes as a form.
                            // While hitting/login, form like HTML response comes, so to avoid it we can comment this.
                            .httpBasic(Customizer.withDefaults()) //Basic authentication page comes like a pop-up
                            ;
                    return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 = User.withUsername("sush")
                //.password("password") //Password as a plain text. No Password Encoder here. But by default, it mandate
               // .passwordEncoder(NoOpPasswordEncoder) //to disable Encoder, used, but depracated
                .password("{noop}password") //using this way, should not use in production.
                .roles("USER") //optional
                .build();
        UserDetails user2 = User.withUsername("mala")
                .password("{noop}9620")
                .roles("USER") //optional
                .build();

        return new InMemoryUserDetailsManager(user1,user2);
    }
}


/*
*
* A) SetUp Security Filter chain with custom configs
* (1) Plug in the filter chain
* (2) Add the authentication for the requests
* (3) Login setUp
* (4) CSRF config if needed
*
*
* B) SetUp UserDetailsService
*
* C) Region Logic user database
*
* */