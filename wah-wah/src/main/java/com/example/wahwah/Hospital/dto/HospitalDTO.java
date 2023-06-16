package com.example.wahwah.Hospital.dto;

import java.time.LocalDateTime;

import com.example.wahwah.Hospital.domain.HospitalEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Data
public class HospitalDTO {
    
    private String hpid;
    private String dutyName;
    private String dutyAddr;
    private String dutyTel1;
    private String createdat;
    private String dutyDiv;
    private String dutyInf;
    private String dutyTel3;
    private String dutyTime1c;
    private String dutyTime1s;
    private String dutyTime2c;
    private String dutyTime2s;
    private String dutyTime3c;
    private String dutyTime3s;
    private String dutyTime4c;
    private String dutyTime4s;
    private String dutyTime5c;
    private String dutyTime5s;
    private String dutyTime6c;
    private String dutyTime6s;
    private String dutyTime7c;
    private String dutyTime7s;
    private String dutyTime8c;
    private String dutyTime8s;
    private String postCdn1;
    private Double wgs84Lon;
    private Double wgs84Lat;
    
    

    public HospitalEntity dtoToEntity(HospitalDTO dto) {

        HospitalEntity hospitalEntity = HospitalEntity.builder()
                                .hpid(dto.getHpid())
                                .dutyName(dto.getDutyName())
                                .dutyAddr(dto.getDutyAddr())
                                .dutyTel1(dto.getDutyTel1())
                                .createdat(dto.getCreatedat())
                                .dutyDiv(dto.getDutyDiv())
                                .dutyInf(dto.getDutyInf())
                                .dutyTel3(dto.getDutyTel3())
                                .dutyTime1c(dto.getDutyTime1c())
                                .dutyTime1s(dto.getDutyTime1s())
                                .dutyTime2c(dto.getDutyTime2c())
                                .dutyTime2c(dto.getDutyTime2s())
                                .dutyTime3c(dto.getDutyTime3c())
                                .dutyTime3c(dto.getDutyTime3s())
                                .dutyTime4c(dto.getDutyTime4c())
                                .dutyTime4c(dto.getDutyTime4c())
                                .dutyTime5c(dto.getDutyTime5c())
                                .dutyTime5c(dto.getDutyTime5s())
                                .dutyTime6c(dto.getDutyTime6c())
                                .dutyTime6c(dto.getDutyTime6s())
                                .dutyTime7c(dto.getDutyTime7c())
                                .dutyTime7c(dto.getDutyTime7s())
                                .dutyTime8c(dto.getDutyTime8c())
                                .dutyTime8c(dto.getDutyTime8s())
                                .postCdn1(dto.getPostCdn1())
                                .wgs84Lon(dto.getWgs84Lon())  
                                .wgs84Lat(dto.getWgs84Lat())  
                                .build();

        return hospitalEntity;
    }


}
