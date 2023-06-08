package com.example.wahwah.Hospital.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HospitalDTO {
    String dutyAddr; // 주소 
    String dutyDiv; // 병원분류
    String dutyEmcls; // 응급의료기관코드
    String dutyEmclsName; // 응급의료기관코드명 
    String dutyEryn; // 응급실운영여부 
    String dutyInf; // 기관설명상세 
    String dutyName; // 기관명

    String dutyTel1; // 대표전화1
    String dutyTel3; // 응급실전화 

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

    String hpid;

    double distance; //현재 거리와 병원 거리를 계산한 값값

    String postCdn1; // 우편번호1,
    String postCdn2; // 우편번호2

    double wgs84Lon; //병원경도
    double wgs84Lat; //병원위도 
}
