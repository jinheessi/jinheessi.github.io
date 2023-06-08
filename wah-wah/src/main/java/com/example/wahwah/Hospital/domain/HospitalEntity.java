package com.example.wahwah.Hospital.domain;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity(name="board")
@Builder
@Table(name="hospital_tbl")
public class HospitalEntity {
	@Id
	
	@Column(name="hospitalId")
    String hospitalId;
	@Column(name="hospitalName")
    String hospitalName;
	@Column(name="hospitalAddress")
    String hospitalAddress;
	@Column(name="hospitalTelno")
    String hospitalTelno;
	@Column(name="hospitalcreatedAt")
    String createdAt;
}