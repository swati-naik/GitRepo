package com.numpyninja.lms.dto;

import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserPictureDTO {
	
    
    private String userId;
	
	
	private String userFileType;
	
	
	private String userFileId;
	
	
	private String userFilePath;
	

	@Lob
	private byte[] userFileData;
}
