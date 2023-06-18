package com.example.wahwah.reservation.dto;

import com.example.wahwah.reservation.entity.ReservationEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private int seqno;
    private String allow;
    private String email;
    private String hpid;
    private String reserve_start;
    private String birth;
    private String gender;
    private String weight;
    private String height;
    private String user_info;
    private String regdate;
    private String month;
    private String day;
    private String rdate;
    private String rtime;
    private String year;



    public ReservationDTO(ReservationEntity entity) {
        this.seqno = entity.getSeqno();
        this.allow = entity.getAllow();
        this.email = entity.getEmail();
        this.hpid = entity.getHpid();
        this.reserve_start = entity.getReserve_start();
        this.birth = entity.getBirth();
        this.gender = entity.getGender();
        this.weight = entity.getWeight();
        this.height = entity.getHeight();
        this.user_info = entity.getUser_info();
        this.regdate = entity.getRegdate();
        this.day = entity.getDay();
	}
	
	public ReservationEntity dtoToEntity(ReservationDTO dto) {
		
		ReservationEntity entity = ReservationEntity.builder()
                                            .seqno(dto.getSeqno())
                                            .allow(dto.getAllow())
											.email(dto.getEmail())
											.hpid(dto.getHpid())
											.reserve_start(dto.getReserve_start())
                                            .birth(dto.getBirth())
                                            .gender(dto.getGender())
                                            .weight(dto.getWeight())
                                            .height(dto.getHeight())
                                            .user_info(dto.getUser_info())
                                            .regdate(dto.getRegdate())
                                            .day(dto.getDay())
                                            .build();
		return entity;
	}



}
