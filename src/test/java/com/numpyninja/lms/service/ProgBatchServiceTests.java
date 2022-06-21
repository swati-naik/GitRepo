package com.numpyninja.lms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.numpyninja.lms.dto.BatchDTO;
import com.numpyninja.lms.entity.Batch;
import com.numpyninja.lms.entity.Program;
import com.numpyninja.lms.mappers.BatchMapper;
import com.numpyninja.lms.repository.ProgramRepository;
import com.numpyninja.lms.services.ProgBatchServices;
import com.numpyninja.lms.repository.ProgBatchRepository;


@ExtendWith(MockitoExtension.class)        //Extension that initializes mocks and handles strict stubbings. This extension is the JUnit Jupiter equivalent of our JUnit4 MockitoJUnitRunner. 
public class ProgBatchServiceTests {
	@Mock
	private ProgBatchRepository batchRepository;
	
	@Mock
	ProgramRepository programRepository;
	
	@Mock
	private BatchMapper batchMapper;
	
	@InjectMocks
	ProgBatchServices batchService;
  
	private static Batch batch1, batch2, batch3;
	private static Program program1, program2; 
	private static BatchDTO batchDTO1, batchDTO2, batchDTO3; 
	private static List<Batch> listOfBatches= new ArrayList();
	private static List<BatchDTO> listOfDTOs = new ArrayList();
	
    @BeforeAll
    public static void setData() {
        long programId1 = 1;
        long programId2 = 2;
        String prg1Desc = "SDET";
        String prg2Desc = "Datascience";
 
    	batchDTO1 = new BatchDTO(1,"01","SDET BATCH 01","In Active",  6, programId1, prg1Desc );
    	batchDTO2 = new BatchDTO(2,"02","SDET BATCH 02","Active", 4, programId1, prg1Desc );
    	batchDTO3 = new BatchDTO(3,"01","DataScience 01","Active", 6, programId2, prg2Desc );
    	listOfDTOs.add(batchDTO1);
    	listOfDTOs.add(batchDTO2);
    	listOfDTOs.add(batchDTO3); 
    	
    	program1 = new Program(); program1.setProgramId((long)1); 
     	program2 = new Program(); program2.setProgramId((long)2);
     	batch1 = new Batch(1,"01","SDET BATCH 01","Active", program1, 6, Timestamp.valueOf("2022-10-04 22:16:02.713"), Timestamp.valueOf("2022-02-04 22:16:02.713") );
     	batch2 = new Batch(2,"02","SDET BATCH 02","Active", program1, 4, Timestamp.valueOf("2022-10-04 22:16:02.713"), Timestamp.valueOf("2021-10-04 22:16:02.713") );
     	batch3 = new Batch( 3, "01","DataScience 01", "Active", program2,6,Timestamp.valueOf("2022-10-04 22:16:02.713"), Timestamp.valueOf("2021-10-04 22:16:02.713") );
     	listOfBatches.add(batch1); 
     	listOfBatches.add(batch2);
     	listOfBatches.add(batch3);
    }
    
    
    @DisplayName("JUnit test for getAllBatches method")
    @Test
    @Order(1)
    public void givenBatchList_WhenGetAllBatches_ThenReturnBatchDTOList(){
        // given 
        given(batchRepository.findAll(Sort.by("batchName"))).willReturn(listOfBatches);
        given ( batchMapper.toBatchDTOs(listOfBatches) ).willReturn(listOfDTOs);
        // when 
        List<BatchDTO> listOfDTOs = batchService.getAllBatches();

        // then 
        assertThat(listOfDTOs).isNotNull();
        assertThat(listOfDTOs.size()).isEqualTo(3);
    }

       
    @DisplayName("JUnit test for getBatchById method")
    @Test
    @Order(2)
    public void givenBatchId_WhenGetBatchById_ThenReturnBatchDTO(){
    	// given
    	Integer batchId = 1;
        given(batchRepository.findById(1)).willReturn(Optional.of(batch1));
        given (  batchMapper.toBatchDTO( batch1 )).willReturn(batchDTO1);
        // when
        BatchDTO batchDTO1 = batchService.findBatchById(batchId);

        // then
        assertThat(batchDTO1).isNotNull();
        assertThat(batchDTO1.getBatchId()).isEqualTo(1);
    }
    
    
	@DisplayName("JUnit test to create Batch")
	@Test
	@Order(3)
	public void givenBatchDTO_WhenSave_ThenReturnSavedBatchDTO() throws Exception {
		// given
		Integer programId = 2; 
		String batchName = "02";
		given(programRepository.findById(2)).willReturn( Optional.of(program2) );
		given(batchRepository.findByBatchNameAndProgram_ProgramId(batchName, programId)).willReturn(null);
		given( batchRepository.save( batch2)).willReturn(batch2);
		// when
		Batch savedBatch = batchService.createBatch(batch2, programId);
		// Then
		assertThat(savedBatch).isNotNull();  
		assertThat(savedBatch.getBatchId()).isEqualTo(2);
	}
    
	/*
	@DisplayName("JUnit test for update Batch")
	//@Test
	@Order(4)
	public void givenUpdatedBatch_WhenUpdateBatch_ThenReturnUpdateBatchObject()  throws Exception {
		// given
		Integer batchId = 1, programIdToUpdate = 2 ;
		given(batchRepository.findById(batchId)).willReturn(Optional.of(batch1));
		given( programRepository.findById( programIdToUpdate )).willReturn(Optional.of( program2 ));
		given( batchRepository.findByBatchNameAndProgram_ProgramId(batch1.getBatchName(), programIdToUpdate)).willReturn(null);
		
		given( batchRepository.save(batch1)).willReturn(batch1);
		// when
		Batch updatedBatch = batchService.updateBatch(batchId,batch1,programIdToUpdate);
		// Then
		assertThat(updatedBatch).isNotNull();
		assertThat(updatedBatch.getProgram().getProgramId()).isEqualTo(2);
	}
	
	
	@DisplayName("JUnit test for delete Batch")
	//@Test
	@Order(5)
	public void givenBatchId_WhenDeleteBatch_ThenDeleteBatchInDB() throws Exception{
		//given
		Integer batchId = 2;
		given(batchRepository.findById(batchId)).willReturn(Optional.of(batch2));
		willDoNothing().given(batchRepository).delete(batch2);
		//when
		batchService.deleteBatch(batchId);
		//then
		verify(batchRepository, times(1)).delete(batch2);
	}*/
}
