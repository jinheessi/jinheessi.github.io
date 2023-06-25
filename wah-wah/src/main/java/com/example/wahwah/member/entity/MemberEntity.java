package com.example.wahwah.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name="member")
@Table(name="tbl_member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberEntity {
	
	@Id
	@Column(name="email",length=50,nullable=false)
	private String email;
	
	@Column(name="username",length=50,nullable=true)
	private String username;
	
	@Column(name="zipcode",length=20,nullable=true)
	private String zipcode;
	
	@Column(name="address",length=200,nullable=true)
	private String address;
	
	@Column(name="telno",length=20,nullable=true)
	private String telno;
	
	@Column(name="nickname",length=20,nullable=true)
	private String nickname;
	
	@Column(name="org_filename",length=200,nullable=true)
	private String org_filename;
	
	@Column(name="stored_filename",length=200,nullable=true)
	private String stored_filename;
	
	@Column(name="filesize",nullable=true)
	private Long filesize;
	
	@Column(name="role",length=20,nullable=false)
	private String role;

	
	
}
