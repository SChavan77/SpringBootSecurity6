package com.spring.security.controller;

import com.spring.security.entity.User;
import com.spring.security.repostitory.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping
public class UserController {

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo=userRepo;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userRepo.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        var u= userRepo.findByUserName(user.getUserName());
        if(!Objects.isNull(u))
          return "LogIn is Successful";
        return "UserName doesn't exists!...Login Failed!";
    }
}
