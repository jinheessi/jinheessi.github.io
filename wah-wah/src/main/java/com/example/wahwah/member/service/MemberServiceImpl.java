package com.example.wahwah.member.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.wahwah.member.dto.ArticleAndFile;
import com.example.wahwah.member.dto.ArticleDTO;
import com.example.wahwah.member.dto.FileDTO;
import com.example.wahwah.member.dto.MemberDTO;
import com.example.wahwah.member.dto.MemberInterface;
import com.example.wahwah.member.entity.AddressEntity;
import com.example.wahwah.member.entity.ArticleEntity;
import com.example.wahwah.member.entity.FileEntity;
import com.example.wahwah.member.entity.repository.AddressRepository;
import com.example.wahwah.member.entity.repository.ArticleRepository;
import com.example.wahwah.member.entity.repository.FileRepository;
import com.example.wahwah.member.entity.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final AddressRepository addressRepository;
	private final FileRepository fileRepository;
	private final ArticleRepository articleRepository;

	@Override
	// 사용자등록
	public void signup(MemberDTO member) {
		member.setRole("USER");
		memberRepository.save(member.dtoToEntity(member));
	}

	@Override
	// 아이디 확인
	public int idCheck(String email) {
		// select * from tbl_member where email = ...
		return memberRepository.findById(email).isEmpty() ? 0 : 1;
	}

	@Override
	// 로그인 정보 확인
	public MemberDTO login(String email) {
		return memberRepository.findById(email).map(member -> new MemberDTO(member)).get();
	}

	@Override
	// 사용자 정보 수정
	public void memberInfoUpdate(MemberDTO member) {
		memberRepository.save(member.dtoToEntity(member));
	}

	@Override
	// 사용자 정보 보기
	public MemberInterface memberInfoView(String email) {
		return memberRepository.memberView(email);
	}

	@Override
	// 주소 검색
	public Page<AddressEntity> addrSearch(int pageNum, int postNum, String addrSearch) {
		PageRequest pageRequest = PageRequest.of(pageNum - 1, postNum, Sort.by(Direction.ASC, "zipcode"));
		return addressRepository.findByRoadContainingOrBuildingContaining(addrSearch, addrSearch, pageRequest);
	}

	// 게시물 목록 조회
	@Override
	public Page<ArticleEntity> listArticleInfo(String type, int pageNum, int postNum) {
		PageRequest pageRequest = PageRequest.of(pageNum - 1, postNum, Sort.by(Direction.ASC, "regdate"));
		Page<ArticleEntity> articleEntities = articleRepository.findByType(type, pageRequest);

		return articleEntities;
	}

	// 게시물 작성
	@Override
	public void writeArticleInfo(ArticleDTO articleDTO, List<FileDTO> FileDTOs) {
		ArticleEntity articleEntity = articleDTO.dtoToEntity();

		// 파일 없을 때
		if (FileDTOs.isEmpty()) {
			articleRepository.save(articleEntity).getSeqno();
		} else { // 파일 있을 때
			Long articleSeqno = articleRepository.save(articleEntity).getSeqno();

			FileDTOs.stream().forEach((item) -> item.setArticleSeqno(articleSeqno));
			List<FileEntity> fileEntities = FileDTOs.stream()
					.map((item) -> item.dtoToEntity()).collect(Collectors.toList());

			fileRepository.saveAll(fileEntities);
		}
	}

	// 게시물 수정
	@Override
	public void modifyArticleInfo(ArticleDTO articleDTO, List<FileDTO> FileDTOs) {
		ArticleEntity articleEntity = articleDTO.dtoToEntity();

		if (FileDTOs.isEmpty()) {
			articleRepository.save(articleEntity).getSeqno();
		} else {
			articleRepository.save(articleEntity).getSeqno();
			FileDTOs.stream().forEach((item) -> item.setArticleSeqno(articleDTO.getSeqno()));

			List<FileEntity> fileEntities = FileDTOs.stream().map((item) -> item.dtoToEntity())
					.collect(Collectors.toList());

			List<String> fileEntitiesOrgFilename = FileDTOs.stream().map((item) -> item.getOrgFilename()).collect(Collectors.toList());

			// List<FileDTO> 안에 없는 파일들은 삭제해줘야 함(수정 => 사용자가 보기에는 필요없는 파일 삭제일 것이기 때문에..)
			List<FileEntity> BeforeModifyFileEntities = fileRepository.findByArticleSeqno(articleDTO.getSeqno())
					.stream()
					.filter((item) -> !fileEntitiesOrgFilename.contains(item.getOrgFilename())).collect(Collectors.toList());

			fileRepository.deleteAll(BeforeModifyFileEntities);
			fileRepository.saveAll(fileEntities);
		}
	}

	// 게시물 삭제
	@Override
	public void deleteArticleInfo(Long articleSeqno) {
		articleRepository.deleteById(articleSeqno);
		fileRepository.deleteByArticleSeqno(articleSeqno);
	}

	// 게시물 조회
	@Override
	public ArticleAndFile viewArticleInfo(Long articleSeqno) {
		ArticleDTO articleDTO = articleRepository.findById(articleSeqno).get().entityToDto();
		List<FileDTO> FileDTOs = fileRepository.findByArticleSeqno(articleSeqno).stream()
				.map((item) -> item.entityToDto()).collect(Collectors.toList());

		return ArticleAndFile.builder().articleDTO(articleDTO).fileDTOs(FileDTOs).build();
	}
}