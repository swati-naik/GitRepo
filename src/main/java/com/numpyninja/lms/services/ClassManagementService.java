package com.numpyninja.lms.services;


import com.numpyninja.lms.dto.ClassScheduleDto;
import com.numpyninja.lms.dto.SkillMasterDto;
import com.numpyninja.lms.entity.ClassSchedule;
import com.numpyninja.lms.entity.ProgBatchEntity;
import com.numpyninja.lms.entity.SkillMaster;
import com.numpyninja.lms.entity.User;
import com.numpyninja.lms.exception.DuplicateResourceFound;
import com.numpyninja.lms.mappers.ClassScheduleMapper;
import com.numpyninja.lms.repository.ClassScheduleRepository;
import com.numpyninja.lms.repository.ProgBatchRepository;
import com.numpyninja.lms.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class ClassManagementService {
    @Autowired
    private ClassScheduleRepository classRepository;
    
    @Autowired 
    ProgBatchRepository batchRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private ClassScheduleMapper classMapper;
    
    
    
    //create class schedules
    public ClassScheduleDto createClass(ClassScheduleDto newClassDto) throws DuplicateResourceFound {
    	 ClassSchedule newClassScheduleEntity;
    	 
    	 ProgBatchEntity batchEntity;
    	 User userEntity;
    	 
		ClassScheduleDto savedclassSchdDto =null;
		 ClassSchedule savedEntity =null;
		 
  if(newClassDto != null && newClassDto.getCsId()!=null && newClassDto.getBatchId()!=null && newClassDto.getClassStaffId()!=null) {
			 

				newClassScheduleEntity= classMapper.toClassScheduleEntity(newClassDto);
				Integer batchIdInClass= newClassDto.getBatchId();
				String StaffInClass = newClassDto.getClassStaffId();
				
				if(batchRepository.existsById(batchIdInClass) && userRepository.existsById(StaffInClass))
				{
					batchEntity = batchRepository.findById(batchIdInClass).get();
					userEntity = userRepository.findById(StaffInClass).get();
					
				List<ClassSchedule> result = 
						classRepository.findByClassIdAndBatchId(newClassScheduleEntity.getCsId(), (newClassScheduleEntity.getBatchInClass()).getBatchId());
				if(result.size()>0) {
					System.out.println("the same combination with ClassId and BatchId exists");
					throw new DuplicateResourceFound("cannot create Class , since already exists with same combination");
				}else {
					
					//save the new class details in repository since this combination is new
					//set the batch,staff entity details to class 
					
					newClassScheduleEntity.setBatchInClass(batchEntity);
					newClassScheduleEntity.setStaffInClass(userEntity);
				
							savedEntity= classRepository.save(newClassScheduleEntity);
				savedclassSchdDto =classMapper.toClassSchdDTO(savedEntity);
				//return savedclassSchdDto;
				}
				}else {
					if(batchIdInClass == null) {
						 System.out.println("BatchId is Fk: no BatchId Exists in batch table "+batchIdInClass);
						 throw new NoSuchElementException("no BatchId Exists in batch table ");
					    }if(StaffInClass == null) {
						 System.out.println("staffId is Fk: no staffId Exists in user table "+StaffInClass);
						 throw new NoSuchElementException("no staffId Exists in user table ");
					  }//end of if
				    }//end of else
				}//end of if
		 	 else {
				System.out.println("check either dto, csId, batchId, staffId is null ");
				throw new NullPointerException();
			}
        return savedclassSchdDto;
    }

		 
		 
		
    //create additional class schedules
    public ClassSchedule createAdditionalClass(ClassSchedule newClass) {
        return classRepository.saveAndFlush(newClass);
    }
    
    //get All Class schedules -not mentioned in Excel
   /* public List<ClassSchedule> getAllClasss() {
        return classRepository.findAll();
    }*/
    
    //get Class by searchString -Not mentioned in Excel
   /* public List<ClassSchedule> getAllClasss(String searchString) {
        return classRepository.findByClassTopicContainingIgnoreCaseOrderByClassTopicAsc(searchString);
    }*/

    //get Class by Id 
    public Optional<ClassSchedule> findClass(Long id) {
        return classRepository.findById(id);
    }

    //Update Class Schedules by Id
    public ClassSchedule updateClass(ClassSchedule updatedClass) {
        return classRepository.save(updatedClass);
    }

    /*public void deleteClass(Long id) {
        classRepository.deleteById(id);
    }*/

}
