package com.example.wahwah.Hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.wahwah.member.dto.HospitalDTO;
import com.example.wahwah.member.dto.MasterDTO;
import com.example.wahwah.member.entity.HospitalEntity;
import com.example.wahwah.member.entity.MasterEntity;
import com.example.wahwah.member.service.MasterService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MasterController {

    //private final MasterService service;
   
    @GetMapping("/")
    public String getMasterLogin() {
    	return "login";
    }
    
    // 병원관리자 회원가입 보여주기
    @GetMapping("/master/signup")
    public void getSignup() {

    }
    @GetMapping("/master/finishSignup")
    public void getFinishSignup() {

    }
    
    @GetMapping("/master/modifyInfo")
    public void getModifyInfo() {}
    
    @GetMapping("/master/searchIDPW")
    public void getSearchIDPW() {}
    /*
    // 병원관리자 회원가입 처리
    @PostMapping("/master/signup")
    public void postSignup(MasterDTO masterDTO) {
        service.signup(masterDTO);
    }

    // 병원관리자 회원탈퇴 보여주기
    @GetMapping("/master/deleteAccount")
    public void getDeleteA() {

    }

    // 병원관리자 회원탈퇴 처리하기
    @PostMapping("/master/deleteAccount")
    public void postDeleteA(MasterDTO masterDTO) {
        service.deleteAccount(masterDTO);
    }


    // 병원정보 조회 보여주기
    @GetMapping("/master/info")
    public void getInfo(HospitalDTO hospitalDTO, Model model) {
        
        model.addAttribute("info", service.info(hospitalDTO));
    }


    // 병원 정보 수정
    @GetMapping("/master/modify")
    public void getModify() {

    }

    // 병원 정보 수정 처리
    @PostMapping("/master/modify")
    public void postModify(HospitalDTO hospitalDTO) {
        service.modify(hospitalDTO);
    }

    // 담당자 정보 조회
    @GetMapping("/master/modify")
    public void getMasterInfo(MasterDTO masterDTO, Model model) {
        model.addAttribute("masterInfo", service.masterInfo(masterDTO));
    
    }

    
    // 병원 담당자 정보 수정
    @PostMapping("/master/masterInfoModify")
    public void getMasterInfoModify() {
        
    }

    // 병원 담당자 정보 수정 처리하기
    @GetMapping("/master/masterInfoModify")
    public void postMasterInfoModify(MasterDTO masterDTO, Model model) {
        service.masterInfoModify(masterDTO);
    }
    */

    
}
