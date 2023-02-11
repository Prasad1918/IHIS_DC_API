package com.prasad.entity;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="CITIZEN_APPS")
public class CitizenAppEntity {
	/*
	 * CASE_NUM INTEGER PRIMARY KEY FULLNAME VARCHAR EMAIL VARCHAR MOBILE_NUM
	 * INTEGER GENDER CHAR DOB DATE SSN INTEGER UNIQUE
	 *  STATE_NAME VARCHAR ACTIVIE_SW
	 * CHAR CREATED_DATE DATE UPDATED_DATE DATE CREATED_BY VARCHAR UPDATED_BY
	 * VARCHAR
	 */
	@Id
	@GeneratedValue
	private Long caseNum;
	private	String fullName;
	private String eamil;
	private	Integer mobileNum;
	private	Character gender;
	private LocalDate dob;
	private Integer ssn;
	private String stateName;
	private Character activeSw;
	private LocalDate createDate;
	private LocalDate updatedDate;
	private String creatredBy;
	private String updatedBy;

}
