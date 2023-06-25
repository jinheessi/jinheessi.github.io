package com.example.wahwah.hospitaluser.dto;

import java.util.Arrays;
import java.util.List;

import com.example.wahwah.hospitaluser.entity.HospitalUserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HospitalUserDTO {
    private String hospitalUserId; // 병원 아이디 
    private String hospitalName; // 병원 이름 
    private String hpid; // 병원 기관 ID 
    private String password; // 병원 비밀번호 
    private String hospitalUserTelno; // 병원 전화번호 
    private String hospitalEmail; // 병원 이메일 
    private String hospitalAddr; //병원 주소 
    private String supervisor; //담당자 이름 
    private String files;  //파일 이름 
    private String verified; //
    
	//생성자를 이용해서 Entity를 this로 이동
    public HospitalUserDTO(HospitalUserEntity hospitalUserEntity) {
		
		this.hospitalUserId = hospitalUserEntity.getHospitalUserId();
		this.hospitalName = hospitalUserEntity.getHospitalName();
        this.hpid = hospitalUserEntity.getHpid();
        this.hospitalAddr = hospitalUserEntity.getHospitalAddr();
        this.password = hospitalUserEntity.getPassword();
        this.hospitalUserTelno = hospitalUserEntity.getHospitalUserTelno();
        this.hospitalEmail = hospitalUserEntity.getHospitalEmail();
        this.supervisor = hospitalUserEntity.getSupervisor();
        this.files =hospitalUserEntity.getHosptial_file();
        this.verified = hospitalUserEntity.getVerified();
	}


    //this --> Entity로 이동
    public HospitalUserEntity dtoToEntity() {
            
        HospitalUserEntity hospitalUserEntity = HospitalUserEntity.builder()
                                        .hospitalUserId(this.getHospitalUserId())
                                        .hospitalName(this.getHospitalName())
                                        .hpid(this.getHpid())
                                        .password(this.getPassword())
                                        .hospitalAddr(this.getHospitalAddr())
                                        .hospitalEmail(this.getHospitalEmail())
                                        .hospitalUserTelno(this.getHospitalUserTelno())
                                        .supervisor(this.getSupervisor())
                                        .verified(this.getVerified())
                                        .hosptial_file(this.getFiles())
                                        .build();
        return hospitalUserEntity;
    }

}
