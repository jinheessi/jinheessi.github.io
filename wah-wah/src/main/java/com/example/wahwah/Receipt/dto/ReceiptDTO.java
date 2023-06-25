package com.example.wahwah.Receipt.dto;

import com.example.wahwah.Receipt.entity.ReceiptEntity;

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
public class ReceiptDTO {
	// 순번
	private int seqno;
	// 병원 아이디
	private String hpid;
	// 병원명
	private String dutyName;
	// 접수 신청일
	private String regdate;
	// 보호자 이름
	private String rname;
	// 환자 이름
	private String pname;
	// 환자 생년월일
	private String birth;
	// 환자 성별
	private String gender;
	// 증상
	private String symptom;
	// 접수 상태
	private String state;
	
	public ReceiptDTO(ReceiptEntity entity) {
		this.seqno = entity.getSeqno();
		this.hpid = entity.getHpid();
		this.dutyName = entity.getDutyName();
		this.regdate = entity.getRegdate();
		this.rname = entity.getRname();
		this.pname = entity.getPname();
		this.birth = entity.getBirth();
		this.gender = entity.getGender();
		this.symptom = entity.getSymptom();
		this.state = entity.getState();
	}
	
	public ReceiptEntity dtoToEntity(ReceiptDTO dto) {
		ReceiptEntity entity = ReceiptEntity.builder()
										.seqno(dto.getSeqno())
										.hpid(dto.getHpid())
										.dutyName(dto.getDutyName())
										.regdate(dto.getRegdate())
										.rname(dto.getRname())
										.pname(dto.getPname())
										.birth(dto.getBirth())
										.gender(dto.getGender())
										.symptom(dto.getSymptom())
										.state(dto.getState())
										.build();
			return entity;
	}
	
}
