package com.numpyninja.lms.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProgramDTO {

		private Integer programId;
		
		private String programName;
		
		private String programDescription;
		private String programStatus;

}

