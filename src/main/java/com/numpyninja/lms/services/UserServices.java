package com.numpyninja.lms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numpyninja.lms.entity.User;
import com.numpyninja.lms.repository.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public Optional<User> getAllUsersById(String Id) {
		return userRepository.findById(Id);
	}
}
