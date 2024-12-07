package com.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.Role;
import com.ams.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(int id) {
        return roleRepository.findById(id).orElse(null);
    }
    
    public Role getRoleByRoleid(Integer roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Role ID: " + roleId));
    }
}
