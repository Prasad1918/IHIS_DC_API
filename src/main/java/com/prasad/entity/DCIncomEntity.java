package com.prasad.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "DC_INCOME")
public class DCIncomEntity {
	@Id
	@GeneratedValue
	private Integer incomeId;
	private Double salryIncome;
	private Double rentIncome;
	private Double propertyIncome;
	private Long caseNum;

}
