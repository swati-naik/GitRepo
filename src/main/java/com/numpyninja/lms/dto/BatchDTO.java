package com.numpyninja.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BatchDTO {
	private Integer batchId;
	
	private String batchName;
	
	private String batchDescription;
	
	private String batchStatus;
	
	private int batchNoOfClasses;
	
	private Long programId;
	private String programName;
	
}
