package com.spring.security.service;

import com.spring.security.entity.User;
import com.spring.security.repostitory.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepo;

    public CustomUserDetailService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        if(Objects.isNull(user)){
            System.out.println("User Not available");
            throw new UsernameNotFoundException("User Not found");
        }
        return new CustomUserDetails(user);
    }
}
