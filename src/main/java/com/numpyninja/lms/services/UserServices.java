package com.numpyninja.lms.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.numpyninja.lms.dto.UserDto;
import com.numpyninja.lms.entity.User;
import com.numpyninja.lms.entity.UserRoleMap;
import com.numpyninja.lms.exception.DuplicateResourceFound;
import com.numpyninja.lms.exception.InvalidDataException;
import com.numpyninja.lms.exception.ResourceNotFoundException;
import com.numpyninja.lms.mappers.UserMapper;
import com.numpyninja.lms.repository.UserRepository;
import com.numpyninja.lms.repository.UserRoleMapRepository;

@Service
public class UserServices {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRoleMapRepository userRoleMapRepository;
	
	@Autowired
	UserMapper userMapper;
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public UserDto getAllUsersById(String Id) throws ResourceNotFoundException {
		Optional<User> userById = userRepository.findById(Id);
		if(userById.isEmpty()) {
			throw new ResourceNotFoundException("User Id " + Id +" not found");
		}
		else {
			UserDto userDto = userMapper.userDto(userById.get());
			return userDto;
		}
		
		/*
		userById.orElseThrow() -> ResourceNotFoundException();
		UserDto userDto = userMapper.userDto(userById.get());
		 return batchRepo.findById(id).orElseThrow(()->new DataNotFoundException("Batch id "+id+" not found "));

		
		return programRepo.findAll()
				.stream()
				.map(this::convertProgramsEntityToDto)
				.collect(Collectors.toList());
		return userDto;
		*/
	}
	
	public List<User> getAllUsersByRole(String roleName) {
		return userRoleMapRepository.findUserRoleMapsByRoleRoleName(roleName).stream()
				.map(userRoleMap -> userRoleMap.getUser()).collect(Collectors.toList());
	}
	
	public List<UserRoleMap> getUsersForProgram(Long programId) {
		List<UserRoleMap> list = userRoleMapRepository.findUserRoleMapsByBatchesProgramProgramId(programId);
    	
		return list.stream().map(userRoleMap -> 
		       { userRoleMap.getBatches().removeIf(batch -> batch.getProgram().getProgramId() == programId); 
		         return userRoleMap;  } ).collect(Collectors.toList());
	}
		

		public UserDto createUser(UserDto newUserDto) throws InvalidDataException, DuplicateResourceFound {
			User newUser = null;
			Date utilDate = new Date();
			
			if(newUserDto!=null) {
				
				/** Checking phone number to prevent duplicate entry **/
				List<User> userList = userRepository.findAll();
				if(userList.size() > 0) {
			    	boolean isPhoneNumberExists = checkDuplicatePhoneNumber(userList, newUserDto.getUserPhoneNumber());
					if(isPhoneNumberExists) {
						throw new DuplicateResourceFound("Failed to create new User as phone number " +newUserDto.getUserPhoneNumber() +" already exists !!");
					}
				}
				/** Checking for valid TimeZone**/
				if (!isTimeZoneValid(newUserDto.getUserTimeZone())) {
					throw new InvalidDataException("Failed to create user, as 'TimeZone' is invalid !! "); 
				}
				/** Checking for valid Visa Status**/
				if(!isVisaStatusValid(newUserDto.getUserVisaStatus())) {
					throw new InvalidDataException("Failed to create user, as 'Visa Status' is invalid !! "); 
				}
				
				newUser  = userMapper.user(newUserDto);
				
				newUser.setCreationTime(new Timestamp(utilDate.getTime()));
				newUser.setLastModTime(new Timestamp(utilDate.getTime()));
	
			}
			else {
				throw new InvalidDataException("User Data not valid ");
			}
			User createdUser = userRepository.save(newUser);
			UserDto createdUserdto = userMapper.userDto(createdUser);
			return createdUserdto;
		}



		public UserDto updateUser(UserDto updateuserDto, String userId) throws ResourceNotFoundException, InvalidDataException {
			User toBeupdatedUser = null;
			Date utilDate = new Date();
			
			System.out.println("userId in update User " + userId);
			
			if(userId == null) {
				throw new InvalidDataException("UserId cannot be blank/null");
			}
			else {
			//if(userId != null) {
				Optional<User> userById = userRepository.findById(userId);
				
				if(userById.isEmpty()) {
					throw new ResourceNotFoundException("UserID: " + userId+ " Not Found");
				}

				else {
					if (!isTimeZoneValid(updateuserDto.getUserTimeZone())) {
						throw new InvalidDataException("Failed to update user, as 'TimeZone' is invalid !! "); 
					}
					if(!isVisaStatusValid(updateuserDto.getUserVisaStatus())) {
						throw new InvalidDataException("Failed to update user, as 'Visa Status' is invalid !! "); 
					}
				
					toBeupdatedUser = userMapper.user(updateuserDto);
					toBeupdatedUser.setUserId(userId);
					toBeupdatedUser.setCreationTime(userById.get().getCreationTime());
					toBeupdatedUser.setLastModTime(new Timestamp(utilDate.getTime())); 
				}
				
				User updatedUser = userRepository.save(toBeupdatedUser);
				UserDto updatedUserDto = userMapper.userDto(updatedUser);
				return updatedUserDto;
			}
			//else {
			//	throw new InvalidDataException("UserId cannot be blank/null");
			//}
		}

		/**Service method for Delete User**/
		public String deleteUser(String userId) throws ResourceNotFoundException {
			
			boolean userExists = userRepository.existsById(userId);

			if(!userExists) {
				throw new ResourceNotFoundException("UserID: " + userId + " doesnot exist ");
			}
			else {
				userRepository.deleteById(userId);
			}
			return userId;
		}
		
		/** Check for already existing phone number**/
		private boolean checkDuplicatePhoneNumber(List<User> userList, long phoneNumber) {
			boolean isUserPresent = false;
			
			for(User user : userList) {
				if(user.getUserPhoneNumber() == phoneNumber) {
					isUserPresent = true;
					break;
				}
			}
			return isUserPresent;
		}
		
		private boolean isTimeZoneValid(String timeZone) {
			Boolean isTimeZoneValid = false;
			List<String> timeZoneList = new ArrayList<String>(List.of("PST", "MST", "CST", "EST", "IST"));
			
			for(String itr : timeZoneList) {
				if(itr.equalsIgnoreCase(timeZone)) {
					isTimeZoneValid = true;
					break;
				}
			}
			return isTimeZoneValid;
			
		}
		
		private boolean isVisaStatusValid(String visa) {
			Boolean isVisaStatusValid = false;
			List<String> visaStatusList = new ArrayList<String>(List.of("Not-Specified", "NA", "GC-EAD", "H4-EAD", "H4", "H1B", 
					"Canada-EAD", "Indian-Citizen", "US-Citizen", "Canada-Citizen"));
			for(String visaStatus : visaStatusList) {
				if(visaStatus.equalsIgnoreCase(visa)) {
					isVisaStatusValid = true;
				}
			}
			return isVisaStatusValid;
		}
	
}
