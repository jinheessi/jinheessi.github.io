package com.example.wahwah.hospitaluser.service;

import com.example.wahwah.Hospital.entity.HospitalEntity;
import com.example.wahwah.hospitaluser.dto.HospitalUserDTO;
import com.example.wahwah.Hospital.dto.HospitalDTO;

public interface HospitalUserService {

    // 병원 아이디 확인
    public Boolean checkId(String hospitalUserId);

    // 병원 관리자 회원가입
    public void signup(HospitalUserDTO hospitalUserDTO);

    public String sendEmail(HospitalUserDTO hospitalUserDTO);

    public String getTempPassword();

    // 병원 관리자 탈퇴하기
    public void deleteAccount(HospitalUserDTO hospitalUserDTO);

    // 병원 담당자 아이디로 조회
    public HospitalUserDTO hospitalUserInfo(HospitalUserDTO hospitalUserDTO);

    //병원 담당자 이메일로로 조회
    public HospitalUserDTO hospitalUserInfoByEmail(HospitalUserDTO hospitalUserDTO);

    public HospitalUserDTO hospitalUserInfoByEmailAndTelno(HospitalUserDTO hospitalUserDTO);

    // 병원 담당자 패스워드 변경경
    public void hospitalUserChangePassword(HospitalUserDTO hospitalUserDTO);

    // 병원 담당자 수정하기
    public void hospitalUserInfoModify(HospitalUserDTO hospitalUserDTO);

    // 병원 수정하기 
    public void hospitalInfoModify(HospitalDTO hospitalDTO);

}
