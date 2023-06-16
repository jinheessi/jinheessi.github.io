package com.example.wahwah.member.dto;

import com.example.wahwah.Hospital.domain.HospitalEntity;
import com.example.wahwah.member.entity.MasterEntity;

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
public class MasterDTO {

    private String user_id;
    private String hospital_id;
    private String password;
    private String hospital_telno;
    private String hospital_email;
    private String supervisor;
    



	//생성자를 이용해서 Entity를 DTO로 이동
    public MasterDTO(MasterEntity MasterEntity) {
		
		this.hospital_id = MasterEntity.getHospital_id();
		this.user_id = MasterEntity.getUser_id();
        this.password = MasterEntity.getPassword();
        this.hospital_telno = MasterEntity.getHospital_telno();
        this.hospital_email = MasterEntity.getHospital_email();
        this.supervisor = MasterEntity.getSupervisor();
	}




    //DTO --> Entity로 이동
    public MasterEntity dtoToEntity(MasterDTO dto) {
            
        MasterEntity hosMasterEntity = MasterEntity.builder()
                                        .hospital_id(dto.getHospital_id())
                                        .user_id(dto.getUser_id())
                                        .password(dto.getPassword())
                                        .hospital_telno(dto.getHospital_telno())
                                        .hospital_email(dto.getHospital_email())
                                        .supervisor(dto.getSupervisor())
                                        .build();
        return hosMasterEntity;
    }

}
