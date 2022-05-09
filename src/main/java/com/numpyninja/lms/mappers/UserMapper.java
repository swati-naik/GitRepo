package com.numpyninja.lms.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.numpyninja.lms.dto.UserDto;
import com.numpyninja.lms.entity.User;

@Mapper(
	    componentModel = "spring"
	)
public interface UserMapper {

	UserDto userDto(User user);
	
	User user(UserDto userDto);
	
	List<UserDto> userDtos(List<User> users);
}
