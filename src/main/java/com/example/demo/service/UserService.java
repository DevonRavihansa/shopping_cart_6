package com.example.demo.service;

import com.example.demo.domain.User;

import java.util.List;

/**
 * Created by Devon Ravihansa on 10/9/2017.
 */
public interface UserService {
    List<User> getAllUsers();
    User getUser(String username);
}
