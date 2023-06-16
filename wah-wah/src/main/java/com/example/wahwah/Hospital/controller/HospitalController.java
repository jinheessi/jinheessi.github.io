package com.example.wahwah.Hospital.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.*;
import com.example.wahwah.Hospital.application.OpenAPI;
import com.example.wahwah.Hospital.dto.HospitalDTO;
import com.example.wahwah.Hospital.dto.HospitalDetailDTO;

@Controller
public class HospitalController {

	OpenAPI openAPI = new OpenAPI();
    
	@GetMapping("/kids/home")
    public void getHome() {}
    
	
    @GetMapping("/kids/pediatric")
    public void getPediatric() {}
    
    @ResponseBody
    @PostMapping("/kids/pediatric")
    public List<HospitalDTO> postPediatricListByAddress(@RequestParam Map<String,String> map, Model model) throws UnsupportedEncodingException {
    	
    	return openAPI.getHospitalListByAddress(map.get("city1"), map.get("county1"));
        
    }
    
    @GetMapping("/kids/emergency")
    public void getEmergency(){}
    
    @ResponseBody
    @PostMapping("/kids/emergency")
    public List<HospitalDTO> postEmergencyListByAddress(@RequestParam Map<String,String> map, Model model) throws UnsupportedEncodingException {
    	return openAPI.getHospitalListByAddress(map.get("city1"), map.get("county1"));
        
    }
    
    @GetMapping("/kids/healthInfo")
    public void getHealthInfo() {}
    
    @GetMapping("/kids/modifyInfo")
    public void getModifyInfo() {}
    
    @GetMapping("/kids/reviewView")
    public void getReviewView(@RequestParam("dutyName") String dutyName, Model model) {
    	
    	model.addAttribute("dutyName", dutyName);    	
    }
    @GetMapping("/kids/notice")
    public void getNotice() {}
    
    @GetMapping("/kids/viewHospital")
    public void getViewHospital(@RequestParam("index") String index, HospitalDTO dto, Model model) {
    	System.out.println(dto);
    	
    	model.addAttribute("index", index);
    	model.addAttribute("hospital", dto);
    }
    
    @GetMapping("/kids/reservation")
    public void getReservation(@RequestParam("dutyName") String dutyName, Model model){
    	model.addAttribute("dutyName", dutyName); 
    } 
    
    @GetMapping("/kids/reservation1")
    public void getReservation1(){
    } 
    
    @GetMapping("/kids/publicHealth")
    public void getPublicHealth(){}
    @GetMapping("/kids/modifyReservation")
    public void getModifyReservation() {
    	
    }
    
    @GetMapping("/kids/myreservation")
    public void getMyReservation() {
    	
    }
    
    @GetMapping("/kids/login")
    public void getLogin() {
    	
    }
    @GetMapping("/kids/pages-login")
    public void getPageLogin() {
    	
    }
    @GetMapping("/kids/pages-register")
    public void getPageRegister() {
    	
    }
    

    
    @PostMapping("/emergency/list")
    @ResponseBody
    public List<HospitalDTO> getEmergencyHospitalListByAddress(@RequestBody Map<String, String> addrData, Model model) {
        List<HospitalDTO> hospitalList = openAPI.getHospitalListByAddress(addrData.get("addr1"), addrData.get("addr2"));
        model.addAttribute("hospitalList", hospitalList);

        return hospitalList;
    }
/*
    @GetMapping("/hospital")
    public HospitalDetailDTO getKidHospitalDetailInformation(@RequestParam String HPID) {
        //HospitalDetailDTO hospitalDetailDTO = openAPI.getHospitalInformation(HPID).get(0);

        return hospitalDetailDTO;
    }*/

    //병원을 검색하는 url => 소아과 병원/응급실이 뒤섞일 거임.
    //프론트엔드 측에서 
    @GetMapping("/hospital/serach")
    @ResponseBody
    public List<HospitalDTO> getHospitalList(@RequestParam String keyword){
        return null;
    }


    // @GetMapping("/emergency")
    // public HospitalDetailDTO getEmergencyHospitalDetailInformation(@RequestParam String HPID) {
    //     HospitalDetailDTO hospitalDetailDTO = openAPI.getHospitalInformation(HPID).get(0);

    //     return hospitalDetailDTO;
    // }
}
