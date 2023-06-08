package com.example.wahwah.member.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="tbl_hospital")
@Table(name="tbl_hospital")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HospitalEntity {
    
    @Id
    @Column(name="hospital_id")
    private String hospital_id;

    @Column(name="hospital_name")
    private String hospital_name;

    @Column(name="hospital_address")
    private String hospital_address;

    @Column(name="hospital_telno")
    private String hospital_telno;

    @Column(name="hospital_created_at")
    private String hospital_created_at;


}
