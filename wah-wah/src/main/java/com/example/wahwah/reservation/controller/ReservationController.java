package com.example.wahwah.reservation.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.web.ServerProperties.Reactive.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.wahwah.Hospital.dto.HospitalDTO;
import com.example.wahwah.Hospital.service.HospitalService;
import com.example.wahwah.hospitaluser.dto.HospitalUserDTO;
import com.example.wahwah.hospitaluser.service.HospitalUserService;
import com.example.wahwah.member.dto.MemberDTO;
import com.example.wahwah.member.dto.MemberInterface;
import com.example.wahwah.member.service.MemberService;
import com.example.wahwah.reservation.dto.ReservationDTO;
import com.example.wahwah.reservation.dto.ReservationInterface;
import com.example.wahwah.reservation.entity.ReservationEntity;
import com.example.wahwah.reservation.service.ReservationService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;
    private final HospitalUserService hospitalUserService;
    private final HospitalService hospitalService;
    private final MemberService mservice;






    // 예약 일별 조회 보여주기
    @ResponseBody
    @PostMapping("/master/reservationViewAll")
    public List<ReservationInterface> getResList(@RequestParam("rdate") String rdate, @RequestParam("rtime") String rtime) {
        System.out.println("rdate = " + rdate);
        System.out.println("rtime = " + rtime);

        // 년 추출
        int year = Integer.parseInt(rdate.split(" ")[0].split("년")[0]);

        // 월 추출
        int month = Integer.parseInt(rdate.substring(rdate.indexOf("년") + 2, rdate.indexOf("월")));
        String newM = "";
        if (month < 10) newM = "0" + month;
        else newM = Integer.toString(month);
        
        // 일 추출
        int day = Integer.parseInt(rdate.substring(rdate.indexOf("월") + 2, rdate.indexOf("일")));

        String a = Integer.toString(year);
        String b = Integer.toString(day);
        String c = Integer.toString(month);
        
        
        String reservestart =rdate + " " + rtime;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        
        System.out.println("***************************************************************");
        System.out.println("***************************************************************");
        System.out.println("***************************************************************");
        System.out.println("***************************************************************");
        
        System.out.println("resList ================== "+service.resList(rdate).toString());

        return service.resList(rdate);
        // return null;
    }
    

    // 나의 예약 정보들 가져오기
    @GetMapping("/kids/myreservation") 
    public void getMyreservation(Model model, HttpSession session) {
        String email = (String)session.getAttribute("email");
        
        
        MemberInterface member = mservice.memberInfoView(email);
        model.addAttribute("member", member);
        model.addAttribute("session", session);
        model.addAttribute("email", email);
        model.addAttribute("reservationList", service.getMyrservationList(email)); // foreach로 처리
    }


    // 예약 정보 수정 화면 보여주기
    @GetMapping("/kids/modifyReservation")
    public void getModifyReservation(@RequestParam("seqno") int seqno, HttpSession session,  Model model) {
    	MemberInterface member = mservice.memberInfoView(String.valueOf(session.getAttribute("email")));
    	model.addAttribute("email", session.getAttribute("email"));
        
        
        model.addAttribute("member", member);
        model.addAttribute("session", session);
        model.addAttribute("seqno", seqno);
    }

    // 예약 정보 수정
    @Transactional
    @ResponseBody
    @PostMapping("/kids/reservationModify")
    public String postReviceReservInfo(@RequestParam("rdate") String rdate, @RequestParam("rtime") String rtime, @RequestParam("seqno") String aa) {


        int seqno = Integer.parseInt(aa);
        String reservestart ="";
        

        reservestart = rdate + rtime;
        System.out.println(reservestart);

        service.reviceReservInfo(reservestart, seqno);
        System.out.println("성공");
        return "{\"msg\":\"GOOD\"}";
    }
    

    // 예약 하기 화면 보여주기
    @GetMapping("/kids/reservation")
    public void getReservation(HospitalDTO hospitalDTO, HttpSession session, Model model) {
    	MemberInterface member = mservice.memberInfoView(String.valueOf(session.getAttribute("email")));
    	model.addAttribute("email", session.getAttribute("email"));
        
        
        model.addAttribute("member", member);
        model.addAttribute("session", session);
        model.addAttribute("hospital", hospitalDTO);
    }

    // 예약 하기
    @PostMapping("/kids/reservation")
    @ResponseBody
    public String postReserv(ReservationDTO dto) {

        System.out.println("dto = " + dto);

        dto.setReservestart(dto.getRdate() + " "+ dto.getRtime());        //postman 실험할때만 주석처리  
        dto.setAllow("Y");
        service.reserve(dto);
        // dto.setDutyName(null);
        return "{\"msg\":\"GOOD\"}";
    }

    // 예약 취소하기
    @Transactional
    @GetMapping("/kids/deleteReservation")
    public String getCancel(@RequestParam("seqno") int seqno) {
        System.out.println("seqno =" + seqno);
        service.cancel(seqno);
        return "redirect:/kids/myreservation";
    }


    // 관리자 예약 취소하기
    @Transactional
    @GetMapping("/master/deleteReservation")
    public String postMasterCancel(@RequestParam("seqno") int seqno) {
        System.out.println("seqno =" + seqno);
        service.cancel(seqno);
        return "redirect:/master/reservationView";
    }
}
