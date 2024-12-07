package com.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.Addmission;
import com.ams.repository.AddmissionRepository;

@Service
public class AddmissionService {

	@Autowired
	public AddmissionRepository addmissionRepository;

	public List<Addmission> findAll() {
		return addmissionRepository.findAll();
	}

	public Addmission findByStudentId(int studentid) {
		return addmissionRepository.findByStudentid(studentid);
	}

}