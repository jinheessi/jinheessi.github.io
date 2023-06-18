package com.example.wahwah.reservation.service;


import java.util.List;

import com.example.wahwah.reservation.dto.ReservationDTO;
import com.example.wahwah.reservation.dto.ReservationInterface;

public interface ReservationService {
    
    // 월+일 예약 조회하기
    public List<ReservationInterface> resList(String yaer, String day, String month);


    // 예약 월별 조회
    public List<ReservationInterface> resMonthList(String yaer, String month);


    // 예약
    public void reserve(ReservationDTO dto);    

    // 예약 취소
    public void cancel(int seqno); 

    // 예약 정보 수정
    public void reviceReservInfo(ReservationDTO dto);
    
    // 나의 예약 정보 가져오기
    public List<ReservationInterface> getMyrservationList(String email);

}
