package com.numpyninja.lms.entity;

import java.math.BigInteger;
import java.security.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.numpyninja.lms.config.UserIDGenerator;

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
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "user_id" ,nullable = false)
    private User user1; 
    
	
	//@OneToMany(targetEntity = User.class ,cascade = CascadeType.ALL)
	//@JoinColumn(name = "user_id" ,referencedColumnName = "userid")
	
	@Column(name = "user_file_path")
	private String userFilePath;
	
	@Transient
	@Lob
	private byte[] userFileData;
	
	
	//private Timestamp creationTime;
	
	

	//private Timestamp lastModTime;
	
	
}
