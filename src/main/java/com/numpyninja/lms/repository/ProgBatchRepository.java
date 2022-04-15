package com.numpyninja.lms.repository;

import com.numpyninja.lms.entity.Batch;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgBatchRepository extends JpaRepository<Batch, Integer>  {
	 // LMSPhase2 changes
	/*static Specification<Batch> hasProgramId(Long programId) {
        return (programBatchEntity, cq, cb) -> cb.equal(programBatchEntity.get("batchProgramId"), programId);
    }*/

    Optional<Batch> findByBatchName(String programName);

    /*List<Batch> findAll(Specification<Batch> hasProgramId);*/   // LMSPhase2 changes

    List<Batch> findByBatchNameContainingIgnoreCaseOrderByBatchIdAsc(String batchName);

    List<Batch> findAllByProgramProgramId ( Integer programId);  // LMSPhase2 changes
    
    //List<Batch> findAllOrderByBatchNameProgramProgramIdAsc();  // throwing error; need to be corrected
}
