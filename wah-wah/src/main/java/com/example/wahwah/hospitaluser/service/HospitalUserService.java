package com.example.wahwah.hospitaluser.service;

import com.example.wahwah.Hospital.entity.HospitalEntity;
import com.example.wahwah.hospitaluser.dto.HospitalUserDTO;
import com.example.wahwah.Hospital.dto.HospitalDTO;

public interface HospitalUserService {

    //병원 아이디 확인 
    public Boolean checkId(String hospitalUserId);
    
    // 병원 관리자 회원가입
    public void signup(HospitalUserDTO hospitalUserDTO);

    // 병원 관리자 탈퇴하기
    public void deleteAccount(HospitalUserDTO hospitalUserDTO);

    //  병원 담당자 조회
    public HospitalUserDTO hospitalUserInfo(HospitalUserDTO hospitalUserDTO);

    //  병원 담당자 수정하기
    public void hospitalUserInfoModify(HospitalUserDTO hospitalUserDTO);
}
