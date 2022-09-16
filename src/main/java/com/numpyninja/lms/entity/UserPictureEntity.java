package com.numpyninja.lms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity

@Getter
@Setter
@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="TBL_LMS_USER_FILES")

public class UserPictureEntity {

	@Id
	 @GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "user_file_id")
	Integer userFileId;

	@Column(name = "user_file_type")
	private String userFileType; 
	
	//@Column(name = "user_id")
	//private String userid;
	
  
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id" , nullable = false)
     private User user;
   
   
    
    /*@ManyToOne(optional=false)
    @JoinColumn(name="user_id"  ,insertable =false ,updatable =false)
    private User usr; */
	
	//@OneToMany(targetEntity = User.class ,cascade = CascadeType.ALL)
	//@JoinColumn(name = "user_id" ,referencedColumnName = "userid")
	
	@Column(name = "user_file_path")
	private String userFilePath;
	
	/*@Transient
	@Lob
	private byte[] userFileData; */
	
	
	//private Timestamp creationTime;
	
	

	//private Timestamp lastModTime;
	
	
}
