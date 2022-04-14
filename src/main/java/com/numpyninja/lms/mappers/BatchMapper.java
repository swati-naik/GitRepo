package com.numpyninja.lms.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


//import com.numpyninja.lms.dto.BatchDto;
import com.numpyninja.lms.entity.ProgBatchEntity;




@Mapper(componentModel = "spring", uses=ProgramMapper.class)
public interface BatchMapper {
	BatchMapper INSTANCE = Mappers.getMapper(BatchMapper.class);
	
	
}
