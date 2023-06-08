package com.example.wahwah.Hospital.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONObject;
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
    
	@GetMapping("/kids/list1")
	public void getList1() {}
    @GetMapping("/kids/list")
    public void getKidHospitalListByAddress(){
    }
    
    @ResponseBody
    @PostMapping("/kids/list")
    public List<HospitalDTO> postKidHospitalListByAddress(@RequestParam Map<String,String> map, Model model) throws UnsupportedEncodingException {
    	
    	return openAPI.getHospitalListByAddress(map.get("city1"), map.get("county1"));
        
    }
    
    @GetMapping("/kids/reviewView")
    public void getReviewView() {}
    
    @GetMapping("/kids/viewHospital")
    public void getViewHospital() {}
    
    @GetMapping("/kids/reservation")
    public void getReservation(){
    } 
    
    @GetMapping("/kids/modifyReservation")
    public void getModifyReservation() {
    	
    }
    
    @GetMapping("/kids/myreservation")
    public void getMyReservation() {
    	
    }
    
    @GetMapping("/kids/login")
    public void getLogin() {
    	
    }


    
    @PostMapping("/emergency/list")
    @ResponseBody
    public List<HospitalDTO> getEmergencyHospitalListByAddress(@RequestBody Map<String, String> addrData) {
        List<HospitalDTO> hospitalList = openAPI.getHospitalListByAddress(addrData.get("addr1"), addrData.get("addr2"));

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
