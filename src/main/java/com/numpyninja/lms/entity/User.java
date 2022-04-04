package com.numpyninja.lms.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TBL_LMS_USER")
public class User {

	@Id	
	@Column
	String userId;
	
	@Column
	String userFirstName;
	
	@Column
	String userLastName;
	
	@Column
	long userPhoneNumber;
	
	@Column
	String userLocation;
	
	@Column
	String userTimeZone;
	
	@Column
	String userLinkedinUrl;
	
	@Column
	String userEduUg;
	
	@Column
	String userEduPg;
	
	@Column
	String userComments;
	
	@Column
	String userVisaStatus;
	
	@Column
	@JsonIgnore
	Timestamp creationTime;
	
	@Column
	@JsonIgnore
	Timestamp lastModTime;

		
	
}
