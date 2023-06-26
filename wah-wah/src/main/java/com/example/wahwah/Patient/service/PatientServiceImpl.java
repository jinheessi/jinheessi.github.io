package com.example.wahwah.Patient.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.wahwah.Patient.dto.PatientDTO;
import com.example.wahwah.Patient.entity.PatientEntity;
import com.example.wahwah.Patient.entity.repository.PatientRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{
	
	
	private final PatientRepository patientRepository;
	
	@Override
	public List<PatientEntity> myBabyCard(String pname){
		return patientRepository.myBabyCard(pname);
	}
	
	@Override
	public PatientEntity viewBabyCard(String bname) {
		return patientRepository.viewBabyCard(bname);
	}
	
	@Override
	public void writeBabyCard(PatientDTO dto) {
		PatientEntity patientEntity = dto.dtoToEntity(dto);
		patientRepository.save(patientEntity);
	}
	@Override
	public void modifyBabyCard(PatientDTO dto) {
		PatientEntity patientEntity = dto.dtoToEntity(dto);
		patientRepository.modifyBabyCard(patientEntity, dto.getBname());
	}
	
	@Override
	public void deleteBabyCard(String bname) {
		patientRepository.deleteBabyCard(bname);
	}
}
