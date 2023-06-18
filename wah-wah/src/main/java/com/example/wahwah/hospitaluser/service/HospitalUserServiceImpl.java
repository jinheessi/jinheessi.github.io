package com.example.wahwah.hospitaluser.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.wahwah.Hospital.dto.HospitalDTO;
import com.example.wahwah.hospitaluser.dto.HospitalUserDTO;
import com.example.wahwah.Hospital.entity.HospitalEntity;
import com.example.wahwah.hospitaluser.entity.HospitalUserEntity;
import com.example.wahwah.Hospital.entity.repository.HospitalRepository;
import com.example.wahwah.hospitaluser.entity.repository.HospitalUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HospitalUserServiceImpl implements HospitalUserService {
    private final HospitalUserRepository hospitalMemberRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 아이디 체크
    @Override
    public Boolean checkId(String hospitalUserId) {
        if (hospitalMemberRepository.findById(hospitalUserId).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    // 병원관리자 회원가입
    @Override
    public void signup(HospitalUserDTO hospitalUserDTO) {
        String cryptedPassword = passwordEncoder.encode(hospitalUserDTO.getPassword());
        hospitalUserDTO.setPassword(cryptedPassword);
        hospitalMemberRepository.save(hospitalUserDTO.dtoToEntity(hospitalUserDTO));
    }

    // 병원 담당자 정보 조회 서비스 호출 => password match => 병원 관리자 탈퇴 서비스 호출
    // 병원 관리자 탈퇴하기
    @Override
    public void deleteAccount(HospitalUserDTO hospitalUserDTO) {
        hospitalMemberRepository.deleteById(hospitalUserDTO.dtoToEntity(hospitalUserDTO).getHospitalUserId());
    }

    // 병원 담당자 정보 조회
    @Override
    public HospitalUserDTO hospitalUserInfo(HospitalUserDTO hospitalUserDTO) {
        HospitalUserEntity hospitalUserEntity = hospitalMemberRepository
                .findById(hospitalUserDTO.getHospitalUserId()).get();
        HospitalUserDTO getHospitalUserDTO = new HospitalUserDTO(hospitalUserEntity);
        return getHospitalUserDTO;
    }

    // 병원 담당자 정보 수정
    @Override
    public void hospitalUserInfoModify(HospitalUserDTO hospitalUserDTO) {
        hospitalMemberRepository.save(hospitalUserDTO.dtoToEntity(hospitalUserDTO));
    }
}
