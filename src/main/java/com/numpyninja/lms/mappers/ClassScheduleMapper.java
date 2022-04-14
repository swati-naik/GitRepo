package com.numpyninja.lms.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.numpyninja.lms.dto.ClassScheduleDto;

import com.numpyninja.lms.entity.ClassSchedule;


@Mapper(componentModel = "spring", uses= {BatchMapper.class,UserMapper.class})
public interface ClassScheduleMapper {
	ClassScheduleMapper INSTANCE = Mappers.getMapper(ClassScheduleMapper.class);
	
	@Mapping(source="batchInClass.batchId",target="batchId")
	@Mapping(source="staffInClass.userId", target="classStaffId")
	ClassScheduleDto toClassSchdDTO(ClassSchedule savedEntity);
		
		@InheritInverseConfiguration
		ClassSchedule toClassScheduleEntity(ClassScheduleDto classSchdDTO);
		 
	   	List<ClassScheduleDto> toClassScheduleDTOList(List<ClassSchedule> classSchdEntites);
		 
		 List<ClassSchedule> toClassScheduleEntityList(List<ClassScheduleDto> classSchdDTOs);
}
