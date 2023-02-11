package com.prasad.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prasad.entity.DCIncomEntity;

public interface DcIncomeRepository extends JpaRepository<DCIncomEntity, Serializable>{

	public DCIncomEntity  findByCaseNum(Long caseNum);//case number is FK in this table so we need to writre method in repo interface
}
