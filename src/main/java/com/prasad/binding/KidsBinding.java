package com.prasad.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class KidsBinding {
	private Integer kidId;
	private String kidName;
	private LocalDate dob;
	private Long kidSsn;
	private String kidGender;
	private Integer caseNum;//FK Relationship for every table
}
