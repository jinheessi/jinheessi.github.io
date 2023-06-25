package com.example.wahwah.hospitaluser.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.wahwah.Hospital.application.OpenAPI;
import com.example.wahwah.Hospital.dto.HospitalDTO;
import com.example.wahwah.Hospital.dto.HospitalSummaryDTO;
import com.example.wahwah.Hospital.service.HospitalService;
import com.example.wahwah.hospitaluser.dto.HospitalUserDTO;
import com.example.wahwah.hospitaluser.dto.PasswordChangeDTO;
import com.example.wahwah.hospitaluser.service.HospitalUserService;
import com.example.wahwah.member.dto.ArticleDTO;
import com.example.wahwah.member.dto.FileDTO;
import com.example.wahwah.member.dto.MemberDTO;
import com.example.wahwah.member.service.MemberService;
import com.example.wahwah.reservation.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;

@Controller
@RequiredArgsConstructor
public class HospitalUserController {

    private final HospitalUserService hospitalUserService;
    private final HospitalService hospitalService;
    private final MemberService memberService;
    private final ReservationService reservationService;
    private final BCryptPasswordEncoder passwordEncoder;
    private OpenAPI openAPI = new OpenAPI();

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

    @GetMapping("/master/searchID")
    public void getSearchID() {
    }

    @PostMapping("/master/searchID")
    @ResponseBody
    public String sendEmail(HospitalUserDTO hospitalUserDTO) {
        System.out.println(hospitalUserDTO.getHospitalEmail());
        System.out.println(hospitalUserDTO.getHospitalUserTelno());
        try {
            HospitalUserDTO hospitalUserInfo = hospitalUserService.hospitalUserInfoByEmailAndTelno(
                    HospitalUserDTO.builder().hospitalEmail(hospitalUserDTO.getHospitalEmail()).hospitalUserTelno(hospitalUserDTO.getHospitalUserTelno()).build());

            //보안을 위해 이메일이 등록되지 않더라도 보냈다고 거짓말 치기  
            if(hospitalUserInfo == null){
                
                return "{\"message\":\"None\"}";
            }
            System.out.println("{\"message\":\"success\", \"id\":\"" + hospitalUserInfo.getHospitalUserId() + "\"}");
            return "{\"message\":\"success\", \"id\":\"" + hospitalUserInfo.getHospitalUserId() + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"message\":\"error\"}";

        }
    }

    //비밀번호 찾는 페이지 
    @GetMapping("/master/searchPW")
    public void getSearchPW() {
    }

    //비밀번호 찾기
    @PostMapping("/master/sendPWEmail")
    @ResponseBody
    public String sendEmail(@RequestBody Map<String,String> data) {
        try {
            HospitalUserDTO hospitalUserDTO = hospitalUserService.hospitalUserInfoByEmail(
                    HospitalUserDTO.builder().hospitalEmail(data.get("hospitalUserEmail")).build());

            //보안을 위해 이메일이 등록되지 않더라도 보냈다고 거짓말 치기  
            if(hospitalUserDTO == null){
                return "{\"message\":\"success\"}";
            }
            String password = hospitalUserService.sendEmail(hospitalUserDTO);
            System.out.println("niniz");
            return "{\"message\":\"success\" , \"password\" : \"" + password + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"message\":\"error\"}";

        }
    }

    @GetMapping("/master/searchPW/success")
    public String sendEmailSuccess() {
        return "master/emailSuccess.html"; // => 임시패스워드 발급할 때 보여줄 홈페이지 
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
            } catch (Exception e) {
                e.printStackTrace();
                return "{\"message\":\"error\"}";
            }
        }
        String addr1 = hospitalUserDTO.getHospitalAddr().split(" ")[0];
        String addr2 = hospitalUserDTO.getHospitalAddr().split(" ")[1];
        String hospitalName = hospitalUserDTO.getHospitalName().replaceAll(" ", "");
        List<HospitalSummaryDTO> hopspitalSummaryDTOList = openAPI.getHospitalListByAddressAndName(addr1, addr2,
                hospitalName);
        if (!hopspitalSummaryDTOList.isEmpty()) {
            hospitalService.putHospitalInfo(hopspitalSummaryDTOList.get(0));
            hospitalUserDTO.setHpid(hopspitalSummaryDTOList.get(0).getHpid());
            hospitalUserService.signup(hospitalUserDTO);
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
    public void getReservationView(Model model, HttpSession session) {
    	 String hospitalUserId = (String) session.getAttribute("hospitalUserId");
         HospitalUserDTO hospitalUserInfo = hospitalUserService
                 .hospitalUserInfo(HospitalUserDTO.builder().hospitalUserId(hospitalUserId).build());

         model.addAttribute("dutyName", hospitalUserInfo.getHospitalName());
         model.addAttribute("hospitalUserInfo", hospitalUserInfo);
         // 오늘 날짜에 해당하는 예약리스트
    }

    // @GetMapping("/master/reservationView/delete")
    // public void getRservationViewDelete(@RequestParam("seqno") int seqno) {
    //     reservationService.cancel(seqno);
    // }

    @GetMapping("/master/modifyInfo")
    public void getModifyInfo(HttpSession session, Model model) {
        String hospitalUserId = (String) session.getAttribute("hospitalUserId");
        HospitalUserDTO hospitalUserInfo = hospitalUserService
                .hospitalUserInfo(HospitalUserDTO.builder().hospitalUserId(hospitalUserId).build());
        HospitalDTO hospitalInfo = hospitalService.getHospitalInfo(hospitalUserInfo.getHpid());
        
        model.addAttribute("hospitalUserInfo", hospitalUserInfo);
        model.addAttribute("hospitalInfo", hospitalInfo);
        model.addAttribute("session", session);
    }

    // 병원 담당자 정보 수정
    @PostMapping("/master/modifyInfo/user")
    @ResponseBody
    public String getMasterInfoModify(HospitalUserDTO hospitalUserDTO) {
        System.out.println(hospitalUserDTO.getHospitalUserId());
        try {
            hospitalUserService.hospitalUserInfoModify(hospitalUserDTO);
            return "{\"message\":\"success\"}";

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"message\":\"error\"}";
        }
    }
    
    
    // 건강 정보 페이지 컨트롤러
    @GetMapping("/master/healthInfo")
    public void getHealthInfo(HttpSession session, Model model) {
    	String hospitalUserId = (String) session.getAttribute("hospitalUserId");
        HospitalUserDTO hospitalUserInfo = hospitalUserService
                .hospitalUserInfo(HospitalUserDTO.builder().hospitalUserId(hospitalUserId).build());
        HospitalDTO hospitalInfo = hospitalService.getHospitalInfo(hospitalUserInfo.getHpid());
        
        model.addAttribute("hospitalUserInfo", hospitalUserInfo);
        model.addAttribute("hospitalInfo", hospitalInfo);
        model.addAttribute("session", session);
    	
    }
    
    
    // 건강 정보 페이지 컨트롤러
    @GetMapping("/master/notice")
    public void getNotice(HttpSession session, Model model) {
    	String hospitalUserId = (String) session.getAttribute("hospitalUserId");
        HospitalUserDTO hospitalUserInfo = hospitalUserService
                .hospitalUserInfo(HospitalUserDTO.builder().hospitalUserId(hospitalUserId).build());
        HospitalDTO hospitalInfo = hospitalService.getHospitalInfo(hospitalUserInfo.getHpid());
        
        model.addAttribute("hospitalUserInfo", hospitalUserInfo);
        model.addAttribute("hospitalInfo", hospitalInfo);
        model.addAttribute("session", session);
    	
    }
    
    
    //공지 사항 작성 컨트롤러
  	@GetMapping("/master/writeNotice")
  	public void getWriteNotice(HttpSession session, Model model) {
  		String hospitalUserId = (String) session.getAttribute("hospitalUserId");
        HospitalUserDTO hospitalUserInfo = hospitalUserService
                .hospitalUserInfo(HospitalUserDTO.builder().hospitalUserId(hospitalUserId).build());
        HospitalDTO hospitalInfo = hospitalService.getHospitalInfo(hospitalUserInfo.getHpid());
        
        model.addAttribute("hospitalUserInfo", hospitalUserInfo);
        model.addAttribute("hospitalInfo", hospitalInfo);
        model.addAttribute("session", session);
  	}
  	
  	
  	@PostMapping("/master/writeNotice")
  	public String postWriteNotice(ArticleDTO articleDTO, @RequestParam("fileUpload") List<MultipartFile> multipartFiles) {

  		articleDTO.setType("announce");
  		String path = "c:\\Repository\\file\\";

  		List<FileDTO> fileDTOs = new ArrayList<>();

  		File targetFile = null;

  		for (MultipartFile multipartFile : multipartFiles) {
  			String orgFilename = multipartFile.getOriginalFilename();
  			String orgFileExtension = orgFilename.substring(orgFilename.lastIndexOf("."));
  			String storedFilename = UUID.randomUUID().toString().replaceAll("-", "") + orgFileExtension;
  			long fileSize = multipartFile.getSize();

  			targetFile = new File(path + storedFilename);
  			try {
  				multipartFile.transferTo(targetFile);
  			} catch (IllegalStateException | IOException e) {
  				return "fail";
  			}

  			FileDTO fileDTO = FileDTO.builder().orgFilename(orgFilename).storedFilename(storedFilename)
  					.fileSize(fileSize).build();
  			fileDTOs.add(fileDTO);
  		}

  		memberService.writeArticleInfo(articleDTO, fileDTOs);

  		
  		return "redirect:/master/notice";
  	}
    

    // 병원 담당자 병원 정보 수정 처리하기
    @PostMapping("/master/modifyInfo/hospital")
    @ResponseBody
    public String postMasterInfoModify(HospitalDTO hospitalDTO) {
        try {
            hospitalService.modify(hospitalDTO);
            return "{\"message\":\"success\"}";

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"message\":\"error\"}";
        }
    }

    @GetMapping("/master/changePassword")
    public void getChangePassword() {
    }

    @PostMapping("/master/changePassword/submit")
    @ResponseBody
    public String postChangePassword(HttpSession session, PasswordChangeDTO passwordChangeDTO) {
        System.out.println(passwordChangeDTO.getPassword());
        System.out.println(passwordChangeDTO.getNewPassword());
        HospitalUserDTO hospitalUserData = hospitalUserService.hospitalUserInfo(
                HospitalUserDTO.builder().hospitalUserId((String) session.getAttribute("hospitalUserId")).build());

        // hospitalUserId가 db에 없다면
        if (hospitalUserData.equals(null)) {
            return "{\"message\":\"error\"}";
        }

        if (passwordChangeDTO.getPassword().equals(passwordChangeDTO.getNewPassword())) {
            return "{\"message\":\"error\"}";
        }

        // 패스워드 확인
        if (passwordEncoder.matches(passwordChangeDTO.getPassword(), hospitalUserData.getPassword())) {
            hospitalUserData.setPassword(passwordEncoder.encode(passwordChangeDTO.getNewPassword()));
            hospitalUserService.hospitalUserChangePassword(hospitalUserData);
            return "{\"message\":\"success\"}";
        } else {
            return "{\"message\":\"error\"}";
        }
    }

    // 병원관리자 회원탈퇴 보여주기
    @GetMapping("/master/deleteUser")
    public void getDeleteAccount() {
    }

    // 병원관리자 회원탈퇴 처리하기
    @PostMapping("/master/deleteUser")
    @ResponseBody
    public String postDeleteA(HospitalUserDTO hospitalUserDTO, HttpSession session) {
        hospitalUserDTO.setHospitalUserId((String) session.getAttribute("hospitalUserId"));
        HospitalUserDTO hospitalUserData = hospitalUserService.hospitalUserInfo(hospitalUserDTO);

        // hospitalUserId가 db에 없다면
        if (hospitalUserData.equals(null)) {
            return "{\"message\":\"error\"}";
        }

        // 패스워드 확인
        if (passwordEncoder.matches(hospitalUserDTO.getPassword(), hospitalUserData.getPassword())) {
            hospitalUserService.deleteAccount(hospitalUserDTO);
            session.invalidate();
            return "{\"message\":\"success\"}";
        } else {
            return "{\"message\":\"error\"}";
        }
    }

    // 회원 삭제 성공 페이지
    @GetMapping("/master/delete")
    public void deleteAccount() {
    }
}
