package com.example.wahwah.Hospital.controller;

import java.io.UnsupportedEncodingException;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.wahwah.Hospital.application.OpenAPI;
import com.example.wahwah.Hospital.dto.HospitalSummaryDTO;
import com.example.wahwah.Hospital.service.HospitalService;
import com.example.wahwah.Review.entity.ReviewEntity;
import com.example.wahwah.Review.service.ReviewService;
import com.example.wahwah.hospitaluser.dto.HospitalUserDTO;
import com.example.wahwah.hospitaluser.service.HospitalUserService;
import com.example.wahwah.member.dto.MemberDTO;
import com.example.wahwah.member.dto.MemberInterface;
import com.example.wahwah.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import com.example.wahwah.Hospital.dto.HospitalDTO;
import com.example.wahwah.Hospital.dto.HospitalDetailDTO;

@RequiredArgsConstructor
@Controller
public class HospitalController {

    HospitalService hospitalService;
    HospitalUserService hospitalUserService;
    OpenAPI openAPI = new OpenAPI();

    private final MemberService mservice;
    private final ReviewService rservice;
    
    // 홈 페이지 컨트롤러
    @GetMapping("/kids/home")
    public void getHome(Model model, HttpSession session) {
    	System.out.println(session.getMaxInactiveInterval());
    	model.addAttribute("session", session);
    	model.addAttribute("day", session.getMaxInactiveInterval()/3600/24);
    	model.addAttribute("time", session.getMaxInactiveInterval()/3600/7);
    	
    }
    
    // 소아과 조회 컨트롤러
    @GetMapping("/kids/pediatric")
    public void getPediatric(Model model, HttpSession session) {

    	String email = (String) session.getAttribute("email");
        MemberInterface member = mservice.memberInfoView(email);
       
        model.addAttribute("member", member);
        model.addAttribute("session", session);
        
       
     }

    @ResponseBody
    @PostMapping("/kids/pediatric")
    public List<HospitalSummaryDTO> postPediatricListByAddress(@RequestParam Map<String, String> map, Model model)
            throws UnsupportedEncodingException {
        return openAPI.getHospitalListByAddress(map.get("city1"), map.get("county1"));

    }

    
    // 응급실 조회 컨트롤러
    @GetMapping("/kids/emergency")
    public void getEmergency(Model model, HttpSession session) {

        String email = (String) session.getAttribute("email");
        MemberInterface member = mservice.memberInfoView(email);
       
        model.addAttribute("member", member);
        model.addAttribute("session", session);
    }

    @ResponseBody
    @PostMapping("/kids/emergency")
    public List<HospitalSummaryDTO> postEmergencyListByAddress(@RequestParam Map<String, String> map, Model model)
            throws UnsupportedEncodingException {
        return openAPI.getEmergencyList(map.get("city1"), map.get("county1"));

    }
    
    
    // 공공 보건소 호출 컨트롤러
    @GetMapping("/kids/publicHealth")
    public void getPublicHealth(HttpSession session, Model model) {
    	String email = (String) session.getAttribute("email");
        MemberInterface member = mservice.memberInfoView(email);
       
        model.addAttribute("member", member);
        model.addAttribute("session", session);
    	
    }
    @ResponseBody
    @PostMapping("/kids/publicHealth")
    public List<HospitalSummaryDTO> viewPublicHealthHospital(@RequestParam Map<String, String> map, Model model) {
        return openAPI.getPublicHospitalList(map.get("city1"), map.get("county1"));
    }

    
    // 병원 상세보기 컨트롤러
    @GetMapping("/kids/viewHospital")
    public void getViewHospital(@RequestParam("index") String index, HospitalDTO dto, HttpSession session, Model model) {
    	
        model.addAttribute("index", index);
        model.addAttribute("hospital", dto);
        model.addAttribute("isUser", "false");
        String email = (String) session.getAttribute("email");
        MemberInterface member = mservice.memberInfoView(email);
        List<ReviewEntity> review = rservice.allCountReview(dto.getHpid());
        
        model.addAttribute("member", member);
        model.addAttribute("session", session);
        model.addAttribute("reviewList", review);
    }
    
    // 건강 정보 페이지 컨트롤러
    @GetMapping("/kids/healthInfo")
    public void getHealthInfo(HttpSession session, Model model) {
    	MemberInterface member = mservice.memberInfoView((String)session.getAttribute("email"));
    	
    	
    	model.addAttribute("session", session);
    	model.addAttribute("member", member);
    	
    }

    // 병원을 검색하는 url => 소아과 병원/응급실이 뒤섞일 거임.

    @GetMapping("/kids/search")
    public List<HospitalSummaryDTO> getHospitalList(@RequestParam String hospitalName) {
        List<HospitalSummaryDTO> hospitalList = openAPI.getHospitaSearchByHospitalName(hospitalName);
        return hospitalList;
    }
}
