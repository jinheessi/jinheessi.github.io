package com.example.wahwah.Patient.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.wahwah.Patient.dto.PatientDTO;
import com.example.wahwah.Patient.entity.PatientEntity;

public interface PatientRepository extends JpaRepository<PatientEntity, String>{

	
	@Query(value = "select * from tbl_patient where pname = :pname", nativeQuery = true)
	public List<PatientEntity> myBabyCard(@Param("pname") String pname);
	
	@Query(value = "select * from tbl_patient where bname = :bname", nativeQuery = true)
	public PatientEntity viewBabyCard(@Param("bname") String bname);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE tbl_patient SET bname=:#{#entity.bname}, height=:#{#entity.height}, weight=:#{#entity.weight}, org_filename=:#{#entity.org_filename}, stored_filename=:#{#entity.stored_filename}, disease=:#{#entity.disease}, baby_info=:#{#entity.baby_info} WHERE bname = :bname", nativeQuery = true)
	public void modifyBabyCard(@Param("entity") PatientEntity entity, @Param("bname") String bname);
	
	
	@Transactional
	@Modifying
	@Query(value = "delete from tbl_patient where bname = :bname", nativeQuery = true)
	public void deleteBabyCard(@Param("bname") String bname);
}
