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
    public List<ReservationInterface> resList(String year, String day, String month) {
        return repository.resList(year, day, month);        
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
    public void reviceReservInfo(ReservationDTO dto) {
        repository.reviceReservInfo(dto.getUser_info(), dto.getWeight(), dto.getHeight(), dto.getReserve_start(), dto.getBirth(), dto.getSeqno());
    }

    // 나의 예약 정보 가져오기
    @Override
    public List<ReservationInterface> getMyrservationList(String email) {
        return repository.getMyrservationList(email);
    }



    
}
