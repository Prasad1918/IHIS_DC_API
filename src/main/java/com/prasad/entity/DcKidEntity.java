package com.prasad.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="DC_KIDS")
public class DcKidEntity {
	@Id
	@GeneratedValue
private Integer kidId;
private String kidName;
private LocalDate dob;
private Long kidSsn;
private String kidGender;
private Long caseNum;//FK Relationship for every table
}
