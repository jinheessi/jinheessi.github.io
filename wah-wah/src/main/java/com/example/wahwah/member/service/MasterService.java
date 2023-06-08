package com.example.wahwah.member.service;

import com.example.wahwah.member.dto.HospitalDTO;
import com.example.wahwah.member.dto.MasterDTO;
import com.example.wahwah.member.entity.HospitalEntity;
import com.example.wahwah.member.entity.MasterEntity;

public interface MasterService {
    
    // 병원 관리자 회원가입
    public void signup(MasterDTO masterDTO);

    // 병원 관리자 탈퇴하기
    public void deleteAccount(MasterDTO masterDTO);

    //  병원 정보 조회
    public HospitalEntity info(HospitalDTO hospitalDTO);

    //  병원 정보 수정
    public void modify(HospitalDTO hospitalDTO);

    //  병원 담당자 조회
    public MasterEntity masterInfo(MasterDTO masterDTO);

    //  병원 담당자 수정하기
    public void masterInfoModify(MasterDTO masterDTO);
}
