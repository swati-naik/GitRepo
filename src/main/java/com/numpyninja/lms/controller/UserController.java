package com.numpyninja.lms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.numpyninja.lms.dto.UserDto;
import com.numpyninja.lms.entity.User;
import com.numpyninja.lms.mappers.UserMapper;
import com.numpyninja.lms.services.UserServices;

@RestController
//@RequestMapping("/users")
public class UserController {
	
	//@Autowired
	//UserServices userServices;
	
	
	private UserMapper mapstructMapper;
	private UserServices userServices;
	
	@Autowired
    public UserController(
            UserMapper mapstructMapper,
            UserServices userServices
      
    ) {
        this.mapstructMapper = mapstructMapper;
        this.userServices = userServices;
 
    }


	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userServices.getAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public UserDto getAllUsersById(@PathVariable String id) {
		Optional<User> userbyId = userServices.getAllUsersById(id);
		System.out.println("userbyId " + userbyId);
		UserDto userDto = mapstructMapper.userDto(userbyId.get());
		System.out.println("userDto " + userDto);;
		return userDto;
	}
}
