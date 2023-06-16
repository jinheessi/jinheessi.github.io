package com.example.wahwah.Hospital.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="hospital")
@Data
@Table(name="hospital")
@Builder
public class HospitalEntity {
    
    @Id
    @Column(name="hpid")
    private String hpid;

    @Column(name="dutyName")
    private String dutyName;

    @Column(name="dutyAddr")
    private String dutyAddr;

    @Column(name="dutyTel1")
    private String dutyTel1;

    @Column(name="createdat")
    private String createdat;
    
    @Column(name="dutyDiv")
    private String dutyDiv;
    
    @Column(name="dutyInf")
    private String dutyInf;
    
    @Column(name="dutyTel3")
    private String dutyTel3;
    
    @Column(name="dutyTime1c")
    private String dutyTime1c;
    
    @Column(name="dutyTime1s")
    private String dutyTime1s;
    
    @Column(name="dutyTime2c")
    private String dutyTime2c;
  
    @Column(name="dutyTime2s")
    private String dutyTime2s;
    
    @Column(name="dutyTime3c")
    private String dutyTime3c;
    
    @Column(name="dutyTime3s")
    private String dutyTime3s;
    
    @Column(name="dutyTime4c")
    private String dutyTime4c;
    
    @Column(name="dutyTime4s")
    private String dutyTime4s;
    
    @Column(name="dutyTime5c")
    private String dutyTime5c;
    
    @Column(name="dutyTime5s")
    private String dutyTime5s;
    
    @Column(name="dutyTime6c")
    private String dutyTime6c;
    
    @Column(name="dutyTime6s")
    private String dutyTime6s;
    
    @Column(name="dutyTime7c")
    private String dutyTime7c;
    
    @Column(name="dutyTime7s")
    private String dutyTime7s;
    
    @Column(name="dutyTime8c")
    private String dutyTime8c;
    
    @Column(name="dutyTime8s")
    private String dutyTime8s;
    
    @Column(name="postCdn1")
    private String postCdn1;
    
    @Column(name="wgs84Lon")
    private Double wgs84Lon;
    
    @Column(name="wgs84Lat")
    private Double wgs84Lat;
    

}
