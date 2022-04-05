package com.numpyninja.lms.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


import com.numpyninja.lms.entity.SkillMaster;
import com.numpyninja.lms.model.SkillMasterModel;

@Mapper(componentModel = "spring")
public interface SkillMasterMapper {
SkillMasterMapper INSTANCE = Mappers.getMapper(SkillMasterMapper.class);
	
	
SkillMasterModel toSkillMasterDTO(SkillMaster savedEntity);
	
	@InheritInverseConfiguration
	SkillMaster toSkillMasterEntity(SkillMasterModel skillMasterDTO);
	 
   	List<SkillMasterModel> toSkillMasterDTOList(List<SkillMaster> skillMasterEntites);
	 
	 List<SkillMaster> toSkillMasterEntityList(List<SkillMasterModel> skillMasterDTOs);
}
