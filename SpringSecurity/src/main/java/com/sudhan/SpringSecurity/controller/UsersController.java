package com.sudhan.SpringSecurity.controller;

import com.sudhan.SpringSecurity.Entity.Users;
import com.sudhan.SpringSecurity.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public Users register(@RequestBody Users users) {
        return usersService.register(users);
    }

    @GetMapping("/list-user")
    public List<Users> register() {
        return usersService.getUser();
    }

    @PostMapping("/login")
    public String  login(@RequestBody Users user){
        return usersService.verify(user);
    }

}
