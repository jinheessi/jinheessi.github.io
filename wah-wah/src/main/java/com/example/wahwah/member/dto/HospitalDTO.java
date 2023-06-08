package com.example.wahwah.member.dto;

import java.time.LocalDateTime;

import com.example.wahwah.member.entity.HospitalEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDTO {
    
    private String hospital_id;
    private String hospital_name;
    private String hospital_address;
    private String hospital_telno;
    private String created_at;

    public HospitalDTO(HospitalEntity entity) {
        this.hospital_id = entity.getHospital_id();
        this.hospital_name = entity.getHospital_name();
        this.hospital_address = entity.getHospital_address();
        this.hospital_telno = entity.getHospital_telno();
        this.created_at = entity.getHospital_created_at();
    }

    public HospitalEntity dtoToEntity(HospitalDTO dto) {

        HospitalEntity hospitalEntity = HospitalEntity.builder()
                                .hospital_id(dto.getHospital_id())
                                .hospital_name(dto.getHospital_name())
                                .hospital_address(dto.getHospital_address())
                                .hospital_telno(dto.getHospital_telno())
                                .hospital_created_at(dto.getCreated_at())
                                .build();

        return hospitalEntity;
    }

}
