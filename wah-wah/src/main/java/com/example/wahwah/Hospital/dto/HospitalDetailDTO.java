package com.example.wahwah.Hospital.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HospitalDetailDTO {
    String hpid; // 기관 ID 
    String dutyName; // 기관명
    String postCdn1; // 우편번호1,
    String postCdn2; // 우편번호2
    String dutyAddr; // 주소 
    String dutyTel1; // 대표전화1
    String dutyTel3;

    String dutyEryn; // 응급실 운영여부

    String dutyTime1c; //월요일 진료시간 
    String dutyTime1s;
    String dutyTime2c; //화요일 진료시간
    String dutyTime2s;
    String dutyTime3c; //수요일 진료시간
    String dutyTime3s;
    String dutyTime4c; //목요일 진료시간
    String dutyTime4s;
    String dutyTime5c; //금요일 진료시간
    String dutyTime5s;
    String dutyTime6c; //토요일 진료시간
    String dutyTime6s;
    String dutyTime7c; //일요일 진료시간
    String dutyTime7s;
    String dutyTime8c; //공휴일 진료시간
    String dutyTime8s; 

    String MKioskTy25; //응급실 사용 가능, 불가 Y/N

    String o001; //응급실 일반병상
    String o002; //응급실 소아병상
    String o008; //신생아 중환자실
    String o009; //소아 중환자실
    String o010; //소아응급전용 중환자실 병상 
    String o020; //소아응급전용입원병상
    String o031; //인공호흡기(소아)

    double wgs84Lon; //병원경도
    double wgs84Lat; //병원위도 

    String hpnicuyn; //신생아중환자실 
}
