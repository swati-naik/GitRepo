package com.numpyninja.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.numpyninja.lms.entity.Assignment;


@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
	
	//public List<Assignment> findByBatchId(int batchId);

}
