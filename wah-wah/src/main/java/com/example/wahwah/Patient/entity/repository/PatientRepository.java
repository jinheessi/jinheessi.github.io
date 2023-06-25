package com.example.wahwah.Patient.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.wahwah.Patient.dto.PatientDTO;
import com.example.wahwah.Patient.entity.PatientEntity;

public interface PatientRepository extends JpaRepository<PatientEntity, String>{
	@Query(value = "select * from tbl_patient where pname = :pname", nativeQuery = true)
	public List<PatientDTO> myBabyCard(@Param("pname") String pname);
	
	@Query(value = "select * from tbl_patient where bname = :bname", nativeQuery = true)
	public PatientDTO viewBabyCard(@Param("bname") String bname);
}
