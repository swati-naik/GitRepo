package com.numpyninja.lms.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import com.numpyninja.lms.entity.Batch;
import com.numpyninja.lms.entity.Program;
import com.numpyninja.lms.repository.ProgBatchRepository;
import com.numpyninja.lms.repository.ProgramRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProgBatchRepositoryTest {

	/*@Autowired
	private BatchRepository batchRepository;

	@Autowired
	ProgramRepository programRepository;

	@DisplayName("JUnit test to create Batch")
	//@Test
	@Order(1)
	public void givenBatchObject_WhenSave_ThenReturnSavedBatch() {
		// given
		Program program1 = programRepository.findById( 1 ).get();
		Batch batch1 = new Batch(1,"01","SDET BATCH 01","Active", program1, 6, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()) );
		// when
		batchRepository.save( batch1);
		// Then
		assertThat(batch1).isNotNull();
		assertThat(batch1.getBatchId()).isEqualTo(1);

		Program program2 = programRepository.findById( 1 ).get();
		Batch batch2 = new Batch(2,"02","SDET BATCH 02","Active", program2, 6, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()) );
		batchRepository.save( batch2);
		assertThat(batch2).isNotNull();
		assertThat(batch2.getBatchId()).isEqualTo(2);
	}

	
	@DisplayName("JUnit test for get all Batches ")
	//@Test
	@Order(2)
	public void givenBatchList_WhenGetAllBatches_ThenReturnBatchesList(){
		List<Batch> list = batchRepository.findAll( Sort.by( "batchId" ) );
		System.out.println("Size:" + list.size());
		assertThat( list.size() ).isGreaterThan(0);
	}


	@DisplayName("JUnit test for get batch by id ")
	//@Test
	@Order(3)
	public void givenBatchId_WhenFindBatchById_ThenReturnBatchObject(){
		//given
		Integer batchId = 1;
		//when
		Batch batch = batchRepository.findById(batchId).get();
		// then - verify the output
		assertThat(batch).isNotNull();
		assertThat(batch.getBatchId()).isEqualTo(1);
	}


	@DisplayName("JUnit test for get Batch by BatchName and ProgramId ")
	//@Test
	@Order(4)
	public void givenBatchNameAndProgramId_WhenFindBatch_ReturnBatchObject() {
		// given
		String batchName = "02";
		Integer programId = 2;
		//when
		Batch batch = batchRepository.findByBatchNameAndProgram_ProgramId(batchName, programId);
		// then
		assertThat(batch).isNotNull();
		assertThat(batch.getBatchName()).isEqualTo("02");
		assertThat(batch.getProgram().getProgramId()).isEqualTo(2);
	}

	
	@DisplayName("JUnit test for update Batch")
	//@Test
	@Order(5)
	public void givenUpdatedBatch_whenUpdateBatch_thenReturnUpdateBatchObject() {
		Batch batch = batchRepository.findById(1).get();
		//given
		batch.setBatchNoOfClasses(7);
		batch.setBatchName("SDET Batch 01 USA");
		//when
		batchRepository.save( batch);
		//then
		assertThat(batch.getBatchName()).isEqualTo("SDET Batch 01 USA");
		assertThat(batch.getBatchNoOfClasses()).isEqualTo(7);
	}

	
	@DisplayName("JUnit test for delete Batch")
	//@Test
	@Order(6)
	public void givenBatchId_whenDeleteBatch_thenDeleteBatchInDB() throws Exception{
		//given
		Integer batchId = 1;
		//when
		Batch batch = batchRepository.findById(batchId).get();
		batchRepository.delete( batch );
		Optional<Batch> batchCheck = batchRepository.findById(batchId);
		//then
		assertThat( batchCheck ).isEmpty();
	}*/
}
