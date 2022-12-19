package com.artere.controllers;

import com.artere.entities.User;
import com.artere.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> listUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
