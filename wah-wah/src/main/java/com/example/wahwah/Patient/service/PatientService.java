package com.example.wahwah.Patient.service;

import java.util.List;

import com.example.wahwah.Patient.dto.PatientDTO;
import com.example.wahwah.Patient.entity.PatientEntity;

public interface PatientService {
	public List<PatientEntity> myBabyCard(String pname);
	public PatientEntity viewBabyCard(String bname);
	public void writeBabyCard(PatientDTO dto);
	public void modifyBabyCard(PatientDTO dto);
	public void deleteBabyCard(String bname);
}
