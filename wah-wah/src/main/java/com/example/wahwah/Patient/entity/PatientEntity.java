package com.example.wahwah.Patient.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name="patient")
@Table(name="tbl_patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientEntity {
	
	@Id
	@Column(name="pname")
	private String pname;
	
	@Column(name="bname")
	private String bname;
	
	@Column(name="birth")
	private String birth;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="height")
	private String height;
	
	@Column(name="weight")
	private String weight;
	
	@Column(name="org_filename")
	private String org_filename;
	
	@Column(name="stored_filename")
	private String stored_filename;
	
	@Column(name="filesize")
	private Long filesize;
	
	@Column(name="disease")
	private String disease;
	
	@Column(name="baby_info")
	private String baby_info;
	
	
}
