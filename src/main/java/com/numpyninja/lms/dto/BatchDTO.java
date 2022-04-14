package com.numpyninja.lms.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
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
	
	private Integer programId;
	private String programName;
	
}
