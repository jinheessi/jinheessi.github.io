package com.example.wahwah.Patient.dto;


import com.example.wahwah.Patient.entity.PatientEntity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDTO {
	
	private int seqno;
	private String pname;
	private String bname;
	private String birth;
	private String height;
	private String weight;
	private String gender;	
	private String org_filename;
	private String stored_filename;
	private Long filesize;
	private String disease;
	private String baby_info;
	
	public PatientDTO(PatientEntity patientEntity) {
		this.seqno = patientEntity.getSeqno();
		this.pname = patientEntity.getPname();
		this.bname = patientEntity.getBname();
		this.birth = patientEntity.getBirth();
		this.height = patientEntity.getHeight();
		this.weight = patientEntity.getWeight();
		this.gender = patientEntity.getGender();
		this.org_filename = patientEntity.getOrg_filename();
		this.stored_filename = patientEntity.getStored_filename();
		this.filesize = patientEntity.getFilesize();		
		this.disease = patientEntity.getDisease();
		this.baby_info = patientEntity.getBaby_info();
				
	}
	
	public PatientEntity dtoToEntity(PatientDTO patientDTO) {
		
		PatientEntity patientEntity = PatientEntity.builder()
											.seqno(patientDTO.getSeqno())
											.pname(patientDTO.getPname())
											.bname(patientDTO.getBname())
											.birth(patientDTO.getBirth())
											.height(patientDTO.getHeight())
											.weight(patientDTO.getWeight())
											.gender(patientDTO.getGender())
											.org_filename(patientDTO.getOrg_filename())
											.stored_filename(patientDTO.getStored_filename())
											.filesize(patientDTO.getFilesize())
											.disease(patientDTO.getDisease())
											.baby_info(patientDTO.getBaby_info())
											.build();
		return patientEntity;
	}
	
}
