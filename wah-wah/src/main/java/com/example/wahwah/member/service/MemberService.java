package com.example.wahwah.member.service;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.example.wahwah.member.dto.ArticleAndFile;
import com.example.wahwah.member.dto.ArticleDTO;
import com.example.wahwah.member.dto.FileDTO;
import com.example.wahwah.member.dto.MemberDTO;
import com.example.wahwah.member.entity.AddressEntity;
import com.example.wahwah.member.entity.ArticleEntity;

public interface MemberService {

	//아이디 확인
	public int idCheck(String userid); 

	//로그인 정보 확인
	public MemberDTO login(String userid); 
	
	//사용자 정보 등록
	public void memberInfoUpdate(MemberDTO member);
	
	//사용자 정보 보기
	public MemberDTO memberInfoView(String userid);
	
	//주소 검색
	public Page<AddressEntity> addrSearch(int pageNum, int postNum, String addrSearch);
	
	//사용자등록
	public void signup(MemberDTO member);

	//관리자 목록 조회
	public Page<ArticleEntity> listArticleInfo(String type, int pageNum, int postNum);

	//관리자 글 조회
	public ArticleAndFile viewArticleInfo(Long articleSeqno);

	//관리자 글 작성
	public void writeArticleInfo(ArticleDTO articleDTO, List<FileDTO> FileDTO);

	//관리자 글 수정 
	public void modifyArticleInfo(ArticleDTO articleDTO, List<FileDTO> FileDTO);

	//관리자 글 삭제 
	public void deleteArticleInfo(Long articleSeqno);
}
