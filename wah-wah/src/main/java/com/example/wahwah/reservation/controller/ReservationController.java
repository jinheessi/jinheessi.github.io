package com.example.wahwah.reservation.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // 예약 일별 조회
    @ResponseBody
    @PostMapping("/test/example")
    public List<ReservationInterface> getResList(@RequestParam("year") String year, @RequestParam("day") String day,
            @RequestParam("month") String month) {
        return service.resList(year, day, month);
    }

    // 예약 달력 조회
    @ResponseBody
    @PostMapping("/test/month")
    public List<ReservationInterface> postMonthResList(@RequestParam("year") String year,
            @RequestParam("month") String month) {
        return service.resMonthList(year, month);
    }

    // 예약 정보 수정
    @Transactional
    @ResponseBody
    @PostMapping("/kids/reservationModify")
    public String postReviceReservInfo(ReservationDTO dto) {
        System.out.println(
                "******************************************************************************************************");
        System.out.println(
                "******************************************************************************************************");
        System.out.println(
                "******************************************************************************************************");
        System.out.println("rdate = " + dto.getRdate());
        // System.out.println("rtime = "+rtime);

        String aa = dto.getRdate();

        // 년 추출
        int year = Integer.parseInt(aa.split(" ")[0].split("년")[0]);

        // 월 추출
        int month = Integer.parseInt(aa.substring(aa.indexOf("년") + 2, aa.indexOf("월")));
        String newM = "";
        if (month < 10) {
            newM = "0" + month;
        } else {
            newM = Integer.toString(month);
        }

        dto.setRdate(newM);
        // 일 추출
        int day = Integer.parseInt(aa.substring(aa.indexOf("월") + 2, aa.indexOf("일")));

        System.out.println("년: " + year);
        System.out.println("월: " + newM);
        System.out.println("일: " + day);

        service.reviceReservInfo(dto);
        System.out.println(dto.toString());
        return "{\"msg\":\"GOOD\"}";
    }

    // 예약 하기
    @ResponseBody
    @PostMapping("/test/ee")
    public void postReserv(ReservationDTO dto) {
        service.reserve(dto);
    }

    // 예약 취소하기
    @Transactional
    @ResponseBody
    @PostMapping("/test/cancel")
    public void postCancel(@RequestParam("seqno") int seqno) {
        System.out.println("seqno =" + seqno);
        service.cancel(seqno);
    }

    @GetMapping("/kids/myreservation")
    @ResponseBody
    public List<ReservationInterface> getMyrservationList(@RequestParam("email") String email, HttpSession session) {
        // String aa = (String)session.getAttribute("email");
        return service.getMyrservationList(email);
    }
}
