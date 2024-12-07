package com.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.Registration;
import com.ams.repository.RegistrationRepository;

@Service
public class RegistrationService {
	
	@Autowired
	public RegistrationRepository registrationRepository;

	public List<Registration> findAll() {
		return registrationRepository.findAll();
	}

	public Registration getRegistrationById(int teacherId) {
		return registrationRepository.findByTeacherid(teacherId);
	}
}
