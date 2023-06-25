package com.example.wahwah.reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.wahwah.reservation.dto.ReservationDTO;
import com.example.wahwah.reservation.dto.ReservationInterface;
import com.example.wahwah.reservation.entity.ReservationEntity;
import com.example.wahwah.reservation.entity.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;



    // 월+일 예약 조회하기
    @Override
    public List<ReservationInterface> resList(String rdate) {
        return repository.resList(rdate);        
    }

    // 월+일 예약 조회하기
    @Override
    public List<ReservationInterface> resMonthList(String year ,String month) {
        return repository.resMonthList(year,month);        
    }


    // 예약 하기
    @Override
    public void reserve(ReservationDTO dto) {
        repository.save(dto.dtoToEntity(dto));
    }

    // 예약 취소하기
    @Override
    public void cancel(int seqno) {
        repository.cancel(seqno);
        
    }

    // 예약 정보 수정
    @Override
    public void reviceReservInfo(String reservestart, int seqno) {
        System.out.println("seqno = " + seqno);
        System.out.println("reservestart = " + reservestart);
        repository.reviceReservInfo(reservestart, seqno);
    }

    // 나의 예약 정보들 가져오기
    @Override
    public List<ReservationEntity> getMyrservationList(String email) {
        return repository.getMyrservationList(email);
    }

    // 나의 예약 정보 가져오기
    @Override
    public ReservationDTO findBySeqno(int seqno) {
        return repository.findBySeqno(seqno);
    }



    
}






