package com.numpyninja.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.numpyninja.lms.entity.UserPictureEntity;

@Repository
public interface UserPictureRepository extends JpaRepository<UserPictureEntity,Integer> {
	
	//public Optional<Assignment> findByAssignmentName(String assignmentName);
	
	//public List<Assignment> findByBatch(Batch batch);

}