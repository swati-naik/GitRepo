package com.numpyninja.lms.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numpyninja.lms.dto.AssignmentDto;
import com.numpyninja.lms.entity.Assignment;
import com.numpyninja.lms.entity.Batch;
import com.numpyninja.lms.exception.ResourceNotFoundException;
import com.numpyninja.lms.mappers.AssignmentMapper;
import com.numpyninja.lms.repository.AssignmentRepository;
import com.numpyninja.lms.repository.ProgBatchRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@Service
public class AssignmentService {
	
	@Autowired
	private AssignmentRepository assignmentRepository;
	
	@Autowired
	private ProgBatchRepository batchRepository;
	
	@Autowired
	private AssignmentMapper assignmentMapper;
		
	//create an assignment 
	public AssignmentDto createAssignment(AssignmentDto assignmentDto) {
		 Assignment assignment = assignmentMapper.toAssignment(assignmentDto);
		 Assignment savedAssignment = this.assignmentRepository.save(assignment);
		 return assignmentMapper.toAssignmentDto(savedAssignment);
	}
	
	//update an assignment
	public AssignmentDto updateAssignment(@RequestBody AssignmentDto assignmentDto, Long assignmentId) {
		this.assignmentRepository.findById(assignmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Assignment", "Id", assignmentId));
		Assignment updatedAssignment = assignmentMapper.toAssignment(assignmentDto);
		this.assignmentRepository.save(updatedAssignment);
		AssignmentDto updatedAssignmentDto = assignmentMapper.toAssignmentDto(updatedAssignment);
		return updatedAssignmentDto;
		
	}
	
	//delete an assignment
	public void deleteAssignment(Long id) {
		Assignment assignment = this.assignmentRepository.findById(id)
							.orElseThrow(() -> new ResourceNotFoundException("Assignment", "Id", id));
		this.assignmentRepository.delete(assignment);
	}

	// get all assignments
    public List<AssignmentDto> getAllAssignments() {
    	List<Assignment> assignments =  this.assignmentRepository.findAll();
    	List<AssignmentDto> assignmentDtos = assignments.stream()
    			.map(assignment -> assignmentMapper.toAssignmentDto(assignment)).collect(Collectors.toList());
        return assignmentDtos;
    }

	//get assignment by id
	public AssignmentDto getAssignmentById(Long id) {
		Assignment assignment = this.assignmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Assignment", "Id", id));
		return assignmentMapper.toAssignmentDto(assignment);
	}
	
	//get assignments for a batch
	public List<AssignmentDto> getAssignmentsForBatch(Integer batchId) {
		Batch batch = this.batchRepository.findById(batchId)
				.orElseThrow(() -> new ResourceNotFoundException("Batch", "Id", batchId));
		List<Assignment> assignments = this.assignmentRepository.findByBatch(batch);
		List<AssignmentDto> assignmentDtos = assignmentMapper.toAssignmentDtoList(assignments);
		return assignmentDtos;
	}
}
