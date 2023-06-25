package com.example.wahwah.hospitaluser.service;

import java.util.List;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private final HospitalUserRepository hospitalUserRepository;
    private final HospitalRepository hospitalRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    // 아이디 체크
    @Override
    public Boolean checkId(String hospitalUserId) {
        if (hospitalUserRepository.findById(hospitalUserId).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String sendEmail(HospitalUserDTO hospitalUserDTO) {
        String tmpPassword = getTempPassword();
        String encodedTmpPassword = passwordEncoder.encode(tmpPassword);

        hospitalUserDTO.setPassword(encodedTmpPassword);
        hospitalUserChangePassword(hospitalUserDTO);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(hospitalUserDTO.getHospitalEmail());
        message.setSubject("[응애응애] 임시 패스워드 발급");
        message.setText("안녕하세요. 응애응애 임시비밀번호 안내 관련 이메일 입니다." + " 회원님의 임시 비밀번호는 "
                + tmpPassword + " 입니다." + "로그인 후에 비밀번호를 변경을 해주세요");
        message.setFrom("telemain12@gmail.com");
        message.setReplyTo("telemain12@gmail.com");
        System.out.println("message" + message);
        mailSender.send(message);
        
        return encodedTmpPassword;
    }

    @Override
    public String getTempPassword() {
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    // 병원관리자 회원가입
    @Override
    public void signup(HospitalUserDTO hospitalUserDTO) {
        String cryptedPassword = passwordEncoder.encode(hospitalUserDTO.getPassword());
        hospitalUserDTO.setPassword(cryptedPassword);
        hospitalUserRepository.save(hospitalUserDTO.dtoToEntity());
    }

    // 병원 담당자 정보 조회 서비스 호출 => password match => 병원 관리자 탈퇴 서비스 호출
    // 병원 관리자 탈퇴하기
    @Override
    public void deleteAccount(HospitalUserDTO hospitalUserDTO) {
        hospitalUserRepository.deleteById(hospitalUserDTO.dtoToEntity().getHospitalUserId());
    }

    public HospitalUserDTO hospitalUserInfoByEmail(HospitalUserDTO hospitalUserDTO) {
        List<HospitalUserEntity> hospitalUserEntities = hospitalUserRepository
                .findByHospitalEmail(hospitalUserDTO.getHospitalEmail());
        if (hospitalUserEntities.isEmpty()) {
            return null;
        } else {
            HospitalUserDTO hospitalUserInfo = new HospitalUserDTO(hospitalUserEntities.get(0));
            return hospitalUserInfo;
        }
    }

    public HospitalUserDTO hospitalUserInfoByEmailAndTelno(HospitalUserDTO hospitalUserDTO) {
        List<HospitalUserEntity> hospitalUserEntities = hospitalUserRepository.findByHospitalEmailAndHospitalUserTelno(hospitalUserDTO.getHospitalEmail(), hospitalUserDTO.getHospitalUserTelno());
        if (hospitalUserEntities.isEmpty()) {
            return null;
        } else {
            HospitalUserDTO hospitalUserInfo = new HospitalUserDTO(hospitalUserEntities.get(0));
            return hospitalUserInfo;
        }
    }

    // 병원 담당자 정보 조회
    @Override
    public HospitalUserDTO hospitalUserInfo(HospitalUserDTO hospitalUserDTO) {
        HospitalUserEntity hospitalUserEntity = hospitalUserRepository
                .findById(hospitalUserDTO.getHospitalUserId()).get();
        HospitalUserDTO getHospitalUserDTO = new HospitalUserDTO(hospitalUserEntity);
        return getHospitalUserDTO;
    }

    // 병원 담당자 패스워드 변경
    @Override
    public void hospitalUserChangePassword(HospitalUserDTO hospitalUserDTO) {
        HospitalUserEntity hospitalUserEntity = hospitalUserDTO.dtoToEntity();
        hospitalUserRepository.save(hospitalUserEntity);
    }

    // 병원 담당자 정보 수정
    @Override
    public void hospitalUserInfoModify(HospitalUserDTO hospitalUserDTO) {
        HospitalUserEntity beforeHospitalUserEntity = hospitalUserRepository
                .findById(hospitalUserDTO.getHospitalUserId()).get();

        beforeHospitalUserEntity.setSupervisor(hospitalUserDTO.getSupervisor());
        beforeHospitalUserEntity.setHospitalEmail(hospitalUserDTO.getHospitalEmail());
        beforeHospitalUserEntity.setHospitalUserTelno(hospitalUserDTO.getHospitalUserTelno());

        hospitalUserRepository.save(beforeHospitalUserEntity);
    }

    @Override
    public void hospitalInfoModify(HospitalDTO hospitalDTO) {
        // HospitalEntity beforeHospitalEntity =
        // hospitalRepository.findById(hospitalDTO.getHpid()).get();
        HospitalEntity afterHospitalEntity = hospitalDTO.dtoToEntity();

        hospitalRepository.save(afterHospitalEntity);
    }
}
