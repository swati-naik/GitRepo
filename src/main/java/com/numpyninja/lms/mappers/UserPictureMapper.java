/*package com.numpyninja.lms.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.numpyninja.lms.dto.UserPictureDTO;
import com.numpyninja.lms.entity.UserPictureEntity;

@Mapper(componentModel = "spring", uses=UserMapper.class)
public interface UserPictureMapper {
	 UserPictureMapper  INSTANCE = Mappers.getMapper( UserPictureMapper .class);
	
	
	 UserPictureDTO toUserPictureDTO(UserPictureEntity file);
	
	//@InheritInverseConfiguration
	
	UserPictureEntity toUserPictureEntity(UserPictureDTO fileDTO);
	 
   	//List<ProgramDTO> toProgramDTOList(List<Program> programEntities);
   	
   	List<UserPictureDTO> toUserPictureDTOList(List<UserPictureEntity> pictureEntities);
	 
	 //List<Program> toPogramEntityList(List<ProgramDTO> ProgramDTOs);
   	List<UserPictureEntity> toUserPictureEntityList(List<UserPictureDTO> pictureDTOs);
}*/