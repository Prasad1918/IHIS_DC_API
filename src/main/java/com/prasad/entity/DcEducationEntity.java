package com.prasad.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="DC_EDUCATION")
public class DcEducationEntity {
	@Id
	@GeneratedValue
	private Integer educationId;
	private	String higestDegree;
	private	String universityName;
	private Integer gradutionYear;
	private Long caseNum;
}
