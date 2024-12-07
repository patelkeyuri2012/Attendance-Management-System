package com.ams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.User;
import com.ams.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && !user.isBlocked() && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
}
