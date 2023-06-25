package com.example.wahwah.member.dto;

import java.time.LocalDateTime;

import com.example.wahwah.member.entity.MemberEntity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
	
	private String email;
	private String username;
	private String zipcode;
	private String address;
	private String telno;
	private String nickname;	
	private String org_filename;
	private String stored_filename;
	private Long filesize;
	private String role;
	
	public MemberDTO(MemberEntity memberEntity) {
		
		this.email = memberEntity.getEmail();
		this.username = memberEntity.getUsername();
		this.zipcode = memberEntity.getZipcode();
		this.address = memberEntity.getAddress();
		this.telno = memberEntity.getTelno();
		this.nickname = memberEntity.getNickname();
		this.org_filename = memberEntity.getOrg_filename();
		this.stored_filename = memberEntity.getStored_filename();
		this.filesize = memberEntity.getFilesize();		
		this.role = memberEntity.getRole();
				
	}
	
	public MemberEntity dtoToEntity(MemberDTO memberDTO) {
		
		MemberEntity memberEntity = MemberEntity.builder()
											.email(memberDTO.getEmail())
											.username(memberDTO.getUsername())
											.zipcode(memberDTO.getZipcode())
											.address(memberDTO.getAddress())
											.telno(memberDTO.getTelno())
											.nickname(memberDTO.getNickname())
											.org_filename(memberDTO.getOrg_filename())
											.stored_filename(memberDTO.getStored_filename())
											.filesize(memberDTO.getFilesize())
											.role(memberDTO.getRole())
											.build();
		return memberEntity;
	}
	
}
