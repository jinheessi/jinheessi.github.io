package com.example.wahwah.Hospital.dto;

import java.time.LocalDateTime;

import com.example.wahwah.Hospital.entity.HospitalEntity;

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
    
    

    public HospitalEntity dtoToEntity() {

        HospitalEntity hospitalEntity = HospitalEntity.builder()
                                .hpid(this.getHpid())
                                .dutyName(this.getDutyName())
                                .dutyAddr(this.getDutyAddr())
                                .dutyTel1(this.getDutyTel1())
                                .createdat(this.getCreatedat())
                                .dutyDiv(this.getDutyDiv())
                                .dutyInf(this.getDutyInf())
                                .dutyTel3(this.getDutyTel3())
                                .dutyTime1c(this.getDutyTime1c())
                                .dutyTime1s(this.getDutyTime1s())
                                .dutyTime2c(this.getDutyTime2c())
                                .dutyTime2s(this.getDutyTime2s())
                                .dutyTime3c(this.getDutyTime3c())
                                .dutyTime3s(this.getDutyTime3s())
                                .dutyTime4c(this.getDutyTime4c())
                                .dutyTime4s(this.getDutyTime4s())
                                .dutyTime5c(this.getDutyTime5c())
                                .dutyTime5s(this.getDutyTime5s())
                                .dutyTime6c(this.getDutyTime6c())
                                .dutyTime6s(this.getDutyTime6s())
                                .dutyTime7c(this.getDutyTime7c())
                                .dutyTime7s(this.getDutyTime7s())
                                .dutyTime8c(this.getDutyTime8c())
                                .dutyTime8s(this.getDutyTime8s())
                                .postCdn1(this.getPostCdn1())
                                .wgs84Lon(this.getWgs84Lon())  
                                .wgs84Lat(this.getWgs84Lat())  
                                .build();

        return hospitalEntity;
    }
}
