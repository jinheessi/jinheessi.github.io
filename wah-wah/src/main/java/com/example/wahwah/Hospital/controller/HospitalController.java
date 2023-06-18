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
import com.example.wahwah.hospitaluser.service.HospitalUserService;
import com.example.wahwah.member.dto.MemberDTO;
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

    @GetMapping("/kids/home")
    public void getHome() {
    }

    @GetMapping("/kids/pediatric")
    public void getPediatric(Model model, HttpSession session) {

        List<MemberDTO> list = new ArrayList<MemberDTO>();

        String email = (String) session.getAttribute("email");
        MemberDTO member = mservice.memberInfoView(email);
        list.add(member);

        model.addAttribute("list", list);
    }
    


    @ResponseBody
    @PostMapping("/kids/pediatric")
    public List<HospitalSummaryDTO> postPediatricListByAddress(@RequestParam Map<String, String> map, Model model)
            throws UnsupportedEncodingException {
        return openAPI.getHospitalListByAddress(map.get("city1"), map.get("county1"));

    }

    @GetMapping("/kids/emergency")
    public void getEmergency() {
    }

    @ResponseBody
    @PostMapping("/kids/emergency")
    public List<HospitalSummaryDTO> postEmergencyListByAddress(@RequestParam Map<String, String> map, Model model)
            throws UnsupportedEncodingException {
        return openAPI.getHospitalListByAddress(map.get("city1"), map.get("county1"));

    }

    // 예약 가능한 병원들 (진료중 o , 진료중 x 둘 다 포함임)
    // 가까운 순으로 예약 가능하게 보여주면 될 듯.. 지역별로 할 필요는 x일지도?
    @GetMapping("/kids/list/reservation")
    @ResponseBody
    public List<HospitalDetailDTO> viewReservationHospital() {
        List<HospitalDetailDTO> hospitalReservationDTO = new ArrayList<>();

        List<HospitalDTO> hospitalDTOList = hospitalService.viewReservationHospital();

        for (HospitalDTO hospitalDTO : hospitalDTOList) {
            HospitalDetailDTO hospitalDetailDTO = openAPI.getHospitalInformation(hospitalDTO.getHpid()).get(0);
            hospitalReservationDTO.add(hospitalDetailDTO);
        }

        return hospitalReservationDTO;
    }
    
    @GetMapping("/kids/receipt")
    public void getReceipt() {}

    // 공공 보건소 호출하는 컨트롤러
    @GetMapping("/kids/list/public")
    @ResponseBody
    public List<HospitalSummaryDTO> viewPublicHealthHospital(@RequestParam Map<String, String> map) {
        List<HospitalSummaryDTO> hospitalSummaryDTOList = openAPI.getPublicHospitalList(map.get("city1"),
                map.get("county1"));

        return hospitalSummaryDTOList;
    }

    @GetMapping("/kids/reviewView")
    public void getReviewView() {
    }

    @GetMapping("/kids/viewHospital")
    public void getViewHospital(@RequestParam("index") String index, HospitalDTO dto, Model model) {
        HospitalDTO hospitalInfo = hospitalService.info(dto.getHpid());

        // 병원 유저일 때의 정보와, API로 불러온 정보가 달라야 함 .
        if (!hospitalInfo.equals(null)) {
            model.addAttribute("index", index);
            model.addAttribute("hospital", hospitalInfo);
            model.addAttribute("isUser", "true");
        } else {
            model.addAttribute("index", index);
            model.addAttribute("hospital", dto);
            model.addAttribute("isUser", "false");
        }
    }

    @GetMapping("/kids/reservation")
    public void getReservation() {
    }

    @GetMapping("/kids/reservation1")
    public void getReservation1() {
    }

    @GetMapping("/kids/modifyReservation")
    public void getModifyReservation() {

    }

    @GetMapping("/kids/pages-login")
    public void getPageLogin() {

    }

    @GetMapping("/kids/pages-register")
    public void getPageRegister() {

    }

    @PostMapping("/emergency/list")
    @ResponseBody
    public List<HospitalSummaryDTO> getEmergencyHospitalListByAddress(@RequestBody Map<String, String> addrData,
            Model model) {
        List<HospitalSummaryDTO> hospitalSummaryDTOList = openAPI.getHospitalListByAddress(addrData.get("addr1"),
                addrData.get("addr2"));
        model.addAttribute("hospitalList", hospitalSummaryDTOList);

        return hospitalSummaryDTOList;
    }
    /*
     * @GetMapping("/hospital")
     * public HospitalDetailDTO getKidHospitalDetailInformation(@RequestParam String
     * HPID) {
     * //HospitalDetailDTO hospitalDetailDTO =
     * openAPI.getHospitalInformation(HPID).get(0);
     * 
     * return hospitalDetailDTO;
     * }
     */

    // 병원을 검색하는 url => 소아과 병원/응급실이 뒤섞일 거임.
    // 프론트엔드 측에서
    @GetMapping("/hospital/serach")
    public List<HospitalSummaryDTO> getHospitalList(@RequestParam String hospitalName) {
        List<HospitalSummaryDTO> hospitalList = openAPI.getHospitaSearchByHospitalName(hospitalName);
        return hospitalList;
    }

    // @GetMapping("/emergency")
    // public HospitalDetailDTO getEmergencyHospitalDetailInformation(@RequestParam
    // String HPID) {
    // HospitalDetailDTO hospitalDetailDTO =
    // openAPI.getHospitalInformation(HPID).get(0);

    // return hospitalDetailDTO;
    // }
}
