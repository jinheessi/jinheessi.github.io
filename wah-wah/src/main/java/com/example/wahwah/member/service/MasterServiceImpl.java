package com.example.wahwah.member.service;
/*package com.example.wahwah.hospitalmember.service;

import org.springframework.stereotype.Service;

import com.example.wahwah.hospitalmember.dto.HospitalDTO;
import com.example.wahwah.hospitalmember.dto.MasterDTO;
import com.example.wahwah.hospitalmember.entity.HospitalEntity;
import com.example.wahwah.hospitalmember.entity.MasterEntity;
import com.example.wahwah.hospitalmember.entity.repository.HospitalRepository;
import com.example.wahwah.hospitalmember.entity.repository.MasterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MasterServiceImpl implements MasterService{

    private final MasterRepository masterRepository;
    private final HospitalRepository hospitalRepository;


    //  병원관리자 회원가입
    @Override
    public void signup(MasterDTO masterDTO) {
        masterRepository.save(masterDTO.dtoToEntity(masterDTO));
    }


    // 병원 관리자 탈퇴하기
    @Override
    public void deleteAccount(MasterDTO masterDTO) {
        masterRepository.deleteById(masterDTO.dtoToEntity(masterDTO).getUser_id());    
    }


    // 병원 정보 조회
    @Override
    public HospitalEntity info(HospitalDTO hospitalDTO) {
        return hospitalRepository.findById(hospitalDTO.dtoToEntity(hospitalDTO).getHospital_id()).get();
    }

    // 병원 정보 수정
    @Override
    public void modify(HospitalDTO hospitalDTO) {
       hospitalRepository.save(hospitalDTO.dtoToEntity(hospitalDTO));
    }

    // 담당자 정보 조회
    @Override
    public MasterEntity masterInfo(MasterDTO masterDTO) {
        return masterRepository.findById(masterDTO.dtoToEntity(masterDTO).getHospital_id()).get();
    }

    // 병원 담당자 정보 수정
    @Override
    public void masterInfoModify(MasterDTO masterDTO) {
        masterRepository.save(masterDTO.dtoToEntity(masterDTO));
    }


    
}
*/