package com.example.wahwah.Hospital.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.wahwah.Hospital.dto.HospitalDTO;
import com.example.wahwah.Hospital.entity.HospitalEntity;
import com.example.wahwah.Hospital.entity.repository.HospitalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    // 병원 정보 조회
    @Override
    public HospitalDTO info(String hpid) {
        HospitalEntity hospitalEntity = hospitalRepository.findById(hpid).get();

        if (hospitalEntity.equals(null)) {
            return null;
        } else {
            return hospitalRepository.findById(hpid).get().entityToDto();

        }
    }

    // 병원 정보 수정
    @Override
    public void modify(HospitalDTO hospitalDTO) {
        hospitalRepository.save(hospitalDTO.dtoToEntity(hospitalDTO));
    }

    @Override
    public List<HospitalDTO> viewReservationHospital() {
        List<HospitalEntity> hospitalEntities = hospitalRepository.findAll();

        if (hospitalEntities.equals(null)) {
            return null;
        }

        List<HospitalDTO> hospitalDTOList = new ArrayList<>();

        for (HospitalEntity hospitalEntity : hospitalEntities) {
            hospitalDTOList.add(HospitalDTO.builder().hpid(hospitalEntity.getHpid()).build());
        }

        return hospitalDTOList;
    }
}
