package com.example.wahwah.member.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wahwah.member.entity.MemberEntity;

public interface MemberRepository  extends JpaRepository<MemberEntity, String> {
	//public MemberEntity findByAuthkey(String authkey);
}
