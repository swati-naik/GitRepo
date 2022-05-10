package com.numpyninja.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.numpyninja.lms.entity.Assignment;
import com.numpyninja.lms.entity.Batch;


@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
	
	public List<Assignment> findByBatch(Batch batch);

}
