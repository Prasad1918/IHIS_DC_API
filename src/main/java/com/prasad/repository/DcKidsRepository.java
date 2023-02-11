package com.prasad.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prasad.entity.DcKidEntity;

public interface DcKidsRepository extends JpaRepository<DcKidEntity, Serializable> {

	public List<DcKidEntity> findByCaseNum(Long caseNum);
}
