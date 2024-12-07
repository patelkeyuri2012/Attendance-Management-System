package com.ams.service;

import com.ams.entity.Role;
import com.ams.entity.User;
import com.ams.entity.UserRole;
import com.ams.repository.UserRepository;
import com.ams.repository.UserRoleRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

	@Autowired
    private UserRepository userRepository;
	
    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserRole findById(int id) {
        return userRoleRepository.findById(id).orElse(null);
    }

    public UserRole update(int userroleid, User user, Role role) {
        UserRole userRole = userRoleRepository.findById(userroleid)
                .orElseThrow(() -> new IllegalArgumentException("Invalid UserRole Id:" + userroleid));

        userRole.setUser(user);
        userRole.setRole(role);
        
        user.setRole(role);
        userRepository.save(user);

        return userRoleRepository.save(userRole);
    }

    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }
}
