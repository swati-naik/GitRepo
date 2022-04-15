package com.numpyninja.lms.services;

import com.numpyninja.lms.entity.Batch;
import com.numpyninja.lms.entity.Program;
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
    public List<Batch> getAllBatches() {
    	return progBatchRepository.findAll();
    	//return progBatchRepository.findAllOrderByProgramProgramIdBatchNameAsc();
    }
    
    public List<Batch> getAllBatches(String searchString) {
    	return progBatchRepository.findByBatchNameContainingIgnoreCaseOrderByBatchIdAsc(searchString);
    }


    //method for get single batch by id
    public Optional<Batch> findBatchById(Integer id) {
        return progBatchRepository.findById(id);
    }

    //method for finding BatchName
    public Optional<Batch> findByProgramBatchName(String name) {
        return progBatchRepository.findByBatchName(name);
    }

    // create new  Batch under Program    // LMSPhase2 changes
    public Batch createBatch(Batch newProgrambatch, Long programId) {
    	Program program = programRepository.findById( programId ).orElseThrow( ()->new RuntimeException("ProgramId:" + programId + " not available; Please give an existing ProgramId" ));
    	newProgrambatch.setProgram(program);
    	return progBatchRepository.save(newProgrambatch);
    }

    //Update new Batch                   // LMSPhase2 changes
    public Batch updateBatch(Batch updatedBatch, Long id) {
    	Program program = programRepository.findById( id ).orElseThrow( ()->new RuntimeException("ProgramId:" + id + " not available; Please give an existing ProgramId" ));
    	updatedBatch.setProgram(program);
    	return progBatchRepository.save(updatedBatch);
    }

    // get Batches by Program ID        // LMSPhase2 changes
    public List<Batch> findBatchByProgramId(Integer programid) {
        //return progBatchRepository.findAll(ProgBatchRepository.hasProgramId(programid));
    	return progBatchRepository.findAllByProgramProgramId(programid);      // query needs to be checked whether its working
    }

    public void deleteProgramBatch(Integer id) {
        progBatchRepository.deleteById(id);
    }

}
	

