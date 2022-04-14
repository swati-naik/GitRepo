package com.numpyninja.lms.services;

import com.numpyninja.lms.entity.ProgBatchEntity;
import com.numpyninja.lms.entity.ProgramEntity;
import com.numpyninja.lms.repository.ProgBatchRepository;
import com.numpyninja.lms.repository.ProgramRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ProgBatchServices {
    @Autowired
    private ProgBatchRepository progBatchRepository;

    @Autowired
    private ProgramRepository programRepository;
    
    // method for All batch
    public List<ProgBatchEntity> getAllBatches() {
        return progBatchRepository.findAll();
    }
    
    public List<ProgBatchEntity> getAllBatches(String searchString) {
        return progBatchRepository.findByBatchNameContainingIgnoreCaseOrderByBatchIdAsc(searchString);
    }


    //method for get single batch by id
    public Optional<ProgBatchEntity> findBatchById(Integer id) {
        return progBatchRepository.findById(id);
    }

    //method for finding BatchName
    public Optional<ProgBatchEntity> findByProgramBatchName(String name) {
        return progBatchRepository.findByBatchName(name);
    }

    // create new  Batch under Program    // LMSPhase2 changes
    public ProgBatchEntity createBatch(ProgBatchEntity newProgrambatch, Long programId) {
    	ProgramEntity program = programRepository.findById( programId ).orElseThrow( ()->new RuntimeException("ProgramId:" + programId + " not available; Please give an existing ProgramId" ));
    	newProgrambatch.setProgram(program);
    	return progBatchRepository.save(newProgrambatch);
    }

    //Update new Batch                   // LMSPhase2 changes
    public ProgBatchEntity updateBatch(ProgBatchEntity updatedProgram, Long id) {
    	ProgramEntity program = programRepository.findById( id ).orElseThrow( ()->new RuntimeException("ProgramId:" + id + " not available; Please give an existing ProgramId" ));
    	updatedProgram.setProgram(program);
    	return progBatchRepository.save(updatedProgram);
    }

    // get Batches by Program ID        // LMSPhase2 changes
    public List<ProgBatchEntity> findBatchByProgramId(Integer programid) {
        //return progBatchRepository.findAll(ProgBatchRepository.hasProgramId(programid));
    	return progBatchRepository.findAllByProgramProgramId(programid);      // query needs to be checked whether its working
    }

    public void deleteProgramBatch(Integer id) {
        progBatchRepository.deleteById(id);
    }

}
	

