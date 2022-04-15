package com.numpyninja.lms.dto;

import javax.validation.constraints.NotBlank;

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
	
	@NotBlank(message = "Batch Name is mandatory")
	private String batchName;
	
	@NotBlank(message = "Batch Description cannot be null")
	private String batchDescription;
	
	private String batchStatus;
	
	private int batchNoOfClasses;
	
	private Long programId;
	private String programName;
	
}
