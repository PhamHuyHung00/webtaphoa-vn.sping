package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    public int getCountLogin(String login) {
        return userRepo.getCountLogin(login);
    }

//    public User findByLoginAndPassword(String login, String password) {
//        User user = userRepo.findByLoginAndPassword(login, password);
//        return user;
//    }


}
