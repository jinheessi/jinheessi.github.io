package com.example.wahwah.reservation.dto;

import com.example.wahwah.reservation.entity.ReservationEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationDTO {

    private int seqno;
    private String allow;
    private String email;
    private String hpid;
    private String reservestart;
    private String birth;
    private String gender;
    private String weight;
    private String height;
    private String userinfo;
    private String regdate;
    // private String month;
    // private String day;
    private String rdate;
    private String rtime;
    
    // private String dutyName;
    private String dutyname;
    // private String year;



    public ReservationDTO(ReservationEntity entity) {
        this.seqno = entity.getSeqno();
        this.allow = entity.getAllow();
        this.email = entity.getEmail();
        this.hpid = entity.getHpid();
        this.reservestart = entity.getReservestart();
        this.birth = entity.getBirth();
        this.gender = entity.getGender();
        this.weight = entity.getWeight();
        this.height = entity.getHeight();
        this.userinfo = entity.getUserinfo();
        this.regdate = entity.getRegdate();
        this.dutyname = entity.getDutyName();
        // this.day = entity.getDay();
	}
	
	public ReservationEntity dtoToEntity(ReservationDTO dto) {
		
		ReservationEntity entity = ReservationEntity.builder()
                                            .seqno(dto.getSeqno())
                                            .allow(dto.getAllow())
											.email(dto.getEmail())
											.hpid(dto.getHpid())
											.reservestart(dto.getReservestart())
                                            .birth(dto.getBirth())
                                            .gender(dto.getGender())
                                            .weight(dto.getWeight())
                                            .height(dto.getHeight())
                                            .userinfo(dto.getUserinfo())
                                            .regdate(dto.getRegdate())
                                            .dutyName(dto.getDutyname())
                                            // .day(dto.getDay())
                                            .build();
		return entity;
	}



}
