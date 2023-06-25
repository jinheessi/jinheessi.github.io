package com.example.wahwah.Patient.service;

import java.util.List;

import com.example.wahwah.Patient.dto.PatientDTO;
import com.example.wahwah.Patient.entity.PatientEntity;

public interface PatientService {
	public List<PatientDTO> myBabyCard(String pname);
	public PatientDTO viewBabyCard(String bname);
	public void writeBabyCard(PatientDTO dto);
}
