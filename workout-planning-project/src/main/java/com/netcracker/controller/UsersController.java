package com.netcracker.controller;

import com.netcracker.model.documents.User;
import com.netcracker.repository.documents.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public String getAllUsers(){
        Iterable<User> users= userRepository.findAll();
        return users.iterator().next().toString();
    }
}
