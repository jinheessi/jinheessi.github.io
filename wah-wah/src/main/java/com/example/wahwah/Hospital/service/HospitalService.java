package com.example.wahwah.Hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.wahwah.Hospital.dto.HospitalDTO;
import com.example.wahwah.Hospital.dto.HospitalSummaryDTO;


public interface HospitalService {
    // 병원 정보 조회
    public HospitalDTO info(String hpid);
    // 병원 정보 수정
    public void modify(HospitalDTO hospitalDTO);
    public void putHospitalInfo(HospitalSummaryDTO hospitalSummaryDTO);
    public List<HospitalDTO> viewReservationHospital();
    public HospitalDTO getHospitalInfo(String hpid);
}
