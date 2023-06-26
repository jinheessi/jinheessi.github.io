package com.example.wahwah.reservation.service;


import java.util.List;

import com.example.wahwah.reservation.dto.ReservationDTO;
import com.example.wahwah.reservation.dto.ReservationInterface;
import com.example.wahwah.reservation.entity.ReservationEntity;

public interface ReservationService {
    
    // 월+일 예약 조회하기
    public List<ReservationInterface> resList(String rdate);


    // 예약 월별 조회
    public List<ReservationEntity> resMonthList(String yaer, String month);

    // 예약
    public void reserve(ReservationDTO dto);    

    // 예약 취소
    public void cancel(int seqno); 

    // 예약 정보 수정
    public void reviceReservInfo(String reservestart, int seqno);
    
    // 나의 예약 정보들 가져오기
    public List<ReservationEntity> getMyrservationList(String email);

    // 나의 예약 정보 가져오기
    public ReservationDTO findBySeqno(int seqno);
}
