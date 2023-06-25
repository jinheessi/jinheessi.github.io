package com.example.wahwah.member.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.wahwah.member.dto.MemberInterface;
import com.example.wahwah.member.entity.MemberEntity;

public interface MemberRepository  extends JpaRepository<MemberEntity, String> {
	@Query(value = "select * from tbl_member where email = :email", nativeQuery=true)
	public MemberInterface memberView(@Param("email") String email);

	
}
