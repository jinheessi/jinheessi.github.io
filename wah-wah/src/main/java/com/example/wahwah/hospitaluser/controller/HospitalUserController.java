package com.example.wahwah.hospitaluser.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.wahwah.Hospital.dto.HospitalDTO;
import com.example.wahwah.Hospital.service.HospitalService;
import com.example.wahwah.hospitaluser.dto.HospitalUserDTO;
import com.example.wahwah.hospitaluser.service.HospitalUserService;
import com.example.wahwah.reservation.service.ReservationService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HospitalUserController {

    private final HospitalUserService hospitalUserService;
    private final HospitalService hospitalService;
    private final ReservationService reservationService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/master/login")
    public void getMasterLogin(HttpSession session) {
        System.out.println(session.getAttribute("hospitalUserId"));
    }

    @PostMapping("/master/login")
    public void postMasterLogin() {
    }

    // 병원관리자 회원가입 보여주기
    @GetMapping("/master/signup")
    public void getSignup() {
    }

    // 병원관리자 아이디 체크
    @GetMapping("/master/checkId")
    @ResponseBody
    public String getCheckId(@RequestParam(name = "id") String id) {
        // 아이디가 이미 있을 경우
        if (hospitalUserService.checkId(id)) {
            return "{\"message\":\"true\"}";

        } else {
            return "{\"message\":\"false\"}";
        }
    }

    // 병원관리자 회원가입 처리
    @PostMapping("/master/signup")
    @ResponseBody
    public String postSignup(HospitalUserDTO hospitalUserDTO,
            @RequestParam(required = false, name = "fileUpload") MultipartFile mprs, RedirectAttributes redirect) {
        System.out.println(hospitalUserDTO.getHospitalName());
        System.out.println(hospitalUserDTO.getHospitalAddr());
        String path = "c:\\Repository\\profile\\";
        String org_filename = "";
        List<String> files = new ArrayList<>();

        long filesize = 0L;

        if (mprs != null) {
            // for (MultipartFile mpr : mprs) {
            try {
                File targetFile = null;
                org_filename = mprs.getOriginalFilename();
                String org_fileExtension = org_filename.substring(org_filename.lastIndexOf("."));
                String stored_filename = UUID.randomUUID().toString().replaceAll("-", "") + org_fileExtension;
                filesize = mprs.getSize();
                targetFile = new File(path + stored_filename);
                mprs.transferTo(targetFile);
                // raw data를 targetFile에서 가진 정보대로 변환
                System.out.println(stored_filename);
                hospitalUserDTO.setFiles(stored_filename);
                hospitalUserDTO.setVerified("Y");
                hospitalUserService.signup(hospitalUserDTO);
            } catch (Exception e) {
                e.printStackTrace();
                return "{\"message\":\"error\"}";
            }
        }
        return "{\"message\":\"success\"}";
    }

    @GetMapping("/master/finishSignup")
    public void getFinishSignUp(@RequestParam("hospitalUserId") String hospitalUserId,
            @RequestParam("hospitalUserTelno") String hospitalUserTelno,
            @RequestParam("hospitalName") String hospitalName, Model model) {

        model.addAttribute("hospitalUserId", hospitalUserId);
        model.addAttribute("hospitalUserTelno", hospitalUserTelno);
        model.addAttribute("hospitalName", hospitalName);
    }

    @GetMapping("/master/reservationView")
    public void getReservationView() {
    }

    @GetMapping("/master/reservationView/delete")
    public void getRservationViewDelete(@RequestParam("seqno") int seqno){
        reservationService.cancel(seqno);
    }

    @GetMapping("/master/modifyInfo")
    public void getModifyInfo(HttpSession session, Model model) {
        String hospitalUserId = (String) session.getAttribute("hospitalUserId");
        HospitalUserDTO hospitalUserInfo = hospitalUserService
                .hospitalUserInfo(HospitalUserDTO.builder().hospitalUserId(hospitalUserId).build());
        model.addAttribute("hospitalUserInfo", hospitalUserInfo);
    }

    // 병원 담당자 정보 수정
    @PostMapping("/master/modifyInfo/user")
    public void getMasterInfoModify(HttpSession session, HospitalUserDTO hospitalUserDTO) {
        hospitalUserService.hospitalUserInfoModify(hospitalUserDTO);
    }

    // 병원 담당자 병원 정보 수정 처리하기
    @PostMapping("/master/info/modify/hospital")
    public void postMasterInfoModify(HospitalDTO hospitalDTO, Model model) {
        hospitalService.modify(hospitalDTO);
    }

    // 병원 정보 수정
    @GetMapping("/master/HospitalInfo/modify")
    public void getHospitalInfoModify() {
    }

    // 병원 정보 수정 처리
    @PostMapping("/master/HospitalInfo/modify")
    public void postHospitalInfoModify(HospitalDTO hospitalDTO) {
        hospitalService.modify(hospitalDTO);
    }

    // 병원관리자 회원탈퇴 보여주기
    @GetMapping("/master/deleteAccount")
    public void getDeleteAccount() {
    }

    // 병원관리자 회원탈퇴 처리하기
    @PostMapping("/master/deleteAccount")
    @ResponseBody
    public String postDeleteA(HospitalUserDTO hospitalUserDTO) {
        HospitalUserDTO hospitalUserData = hospitalUserService.hospitalUserInfo(hospitalUserDTO);

        // hospitalUserId가 db에 없다면
        if (hospitalUserData.equals(null)) {
            return "{\"message\":\"잘못된 접근입니다.\"}";
        }

        // 패스워드 확인
        if (passwordEncoder.matches(hospitalUserDTO.getPassword(), hospitalUserData.getPassword())) {
            hospitalUserService.deleteAccount(hospitalUserDTO);
            return "redirect:/master/delete";
        } else {
            return "{\"message\":\"비밀번호가 틀렸습니다.\"}";
        }
    }

    // 회원 삭제 성공 페이지
    @GetMapping("/master/delete")
    public void deleteAccount() {
    }

    // 담당자 정보 조회
    @GetMapping("/master/info")
    public void getMasterInfo(HospitalUserDTO hospitalUserDTO, Model model) {
        model.addAttribute("masterInfo", hospitalUserService.hospitalUserInfo(hospitalUserDTO));
    }

}
