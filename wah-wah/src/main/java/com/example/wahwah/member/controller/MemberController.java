package com.example.wahwah.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import com.example.wahwah.Hospital.application.OpenAPI;
import com.example.wahwah.hospitaluser.dto.HospitalUserDTO;
import com.example.wahwah.member.dto.ArticleDTO;
import com.example.wahwah.member.dto.FileDTO;
import com.example.wahwah.member.dto.MemberDTO;
import com.example.wahwah.member.entity.AddressEntity;
import com.example.wahwah.member.service.*;
import com.example.wahwah.member.util.PageUtil;
import jakarta.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class MemberController {

	private final MemberService memberService;
	private OpenAPI openAPI;

	// 첫페이지
	@GetMapping("/")
	public String home() throws Exception {

		return "redirect:/kids/home";
	}

	// 로그인
	@GetMapping("/kids/login")
	public void getLogIn() {
	}

	// 로그인
	@PostMapping("/kids/login")
	public void postLogIn() {
	}

	// 로그아웃
	@GetMapping("/kids/logout")
	public String getLogout(HttpSession session, Model model) {

		session.invalidate(); // 모든 세션 종료--> 로그아웃

		return "redirect:/";
	}

	// 사용자 정보 수정 보기
	@GetMapping("/kids/modifyInfo")
	public void gerMemberInfoViewModify(Model model, HttpSession session) {

		String email = (String) session.getAttribute("email");
		MemberDTO member = memberService.memberInfoView(email);

		model.addAttribute("member", member);
	}

	// 사용자 정보 수정
	@PostMapping("/kids/modifyInfo")
	public String postMemberInfoModify(MemberDTO member) {

		memberService.memberInfoUpdate(member);
		return "redirect:/kids/modifyInfo";

	}

	// 우편번호 검색
	@GetMapping("/kids/addrSearch")
	public void getSearchAddr(@RequestParam("addrSearch") String addrSearch,
			@RequestParam("page") int pageNum, Model model) throws Exception {

		int postNum = 5;
		int listCount = 10;

		PageUtil page = new PageUtil();

		Page<AddressEntity> list = memberService.addrSearch(pageNum, postNum, addrSearch);
		int totalCount = (int) list.getTotalElements();

		model.addAttribute("list", list);
		model.addAttribute("pageListView", page.getPageAddress(pageNum, postNum, listCount, totalCount, addrSearch));

	}

	// 건강 정보 조회
	@GetMapping("/kids/healthInfo")
	public void getHealthInfo() {
		String type = "posting";
		memberService.listArticleInfo(type, 0, 0);
	}

	// 건강 정보 작성, 수정, 삭제 버튼은 thymeleaf에서 Session이 master면 보이도록 처리해줘야 한다.
	// 건강 정보 작성
	@GetMapping("/kids/healthInfo/write")
	public void getWriteHealthInfo() {
	}

	@PostMapping("/kids/healthInfo/write")
	public String postWriteHealthInfo(ArticleDTO articleDTO,
			@RequestParam("fileUpload") List<MultipartFile> multipartFiles) {

		articleDTO.setType("posting");
		String path = "c:\\Repository\\file\\";

		List<FileDTO> fileDTOs = new ArrayList<>();

		File targetFile = null;

		for (MultipartFile multipartFile : multipartFiles) {
			String orgFilename = multipartFile.getOriginalFilename();
			String orgFileExtension = orgFilename.substring(orgFilename.lastIndexOf("."));
			String storedFilename = UUID.randomUUID().toString().replaceAll("-", "") + orgFileExtension;
			long fileSize = multipartFile.getSize();

			targetFile = new File(path + storedFilename);
			try {
				multipartFile.transferTo(targetFile);
			} catch (IllegalStateException | IOException e) {
				return "fail";
			}

			FileDTO fileDTO = FileDTO.builder().orgFilename(orgFilename).storedFilename(storedFilename)
					.fileSize(fileSize).build();
			fileDTOs.add(fileDTO);
		}

		memberService.writeArticleInfo(articleDTO, fileDTOs);
		return "success";

	}

	@GetMapping("/kids/healthInfo/modify")
	public void getModifyHealthInfo(@RequestParam("seqno") Long seqno, Model model) {
		model.addAttribute("article", memberService.viewArticleInfo(seqno).getArticleDTO());
		model.addAttribute("files", memberService.viewArticleInfo(seqno).getFileDTOs());
	}

	@PostMapping("/kids/healthInfo/modify")
	public String postModifyHealthInfo(ArticleDTO articleDTO,
			@RequestParam("fileUpload") List<MultipartFile> multipartFiles) {
		articleDTO.setType("posting");
		String path = "c:\\Repository\\file\\";

		List<FileDTO> fileDTOs = new ArrayList<>();

		File targetFile = null;

		for (MultipartFile multipartFile : multipartFiles) {
			String orgFilename = multipartFile.getOriginalFilename();
			String orgFileExtension = orgFilename.substring(orgFilename.lastIndexOf("."));
			String storedFilename = UUID.randomUUID().toString().replaceAll("-", "") + orgFileExtension;
			long fileSize = multipartFile.getSize();

			targetFile = new File(path + storedFilename);
			try {
				multipartFile.transferTo(targetFile);
			} catch (IllegalStateException | IOException e) {
				return "fail";
			}

			FileDTO fileDTO = FileDTO.builder().orgFilename(orgFilename).storedFilename(storedFilename)
					.fileSize(fileSize).build();
			fileDTOs.add(fileDTO);
		}

		memberService.modifyArticleInfo(articleDTO, fileDTOs);
		return "success";
	}

	@GetMapping("/kids/healthInfo/delete")
	public void getHealthInfoDelete(@RequestParam("seqno") Long seqno) {
		memberService.deleteArticleInfo(seqno);
	}

	// -------------------------------- 공지사항 -----------------------------------
	@GetMapping("/kids/notice")
	public void getAnnounceInfo() {
		// String type = "announce";
		// memberService.listArticleInfo(type, 0, 0);
	}

	@GetMapping("/kids/notice/view")
	public void getAnnounceInfoList(@RequestParam("seqno") Long seqno, Model model) {
		model.addAttribute("announce", memberService.viewArticleInfo(seqno).getArticleDTO());
		model.addAttribute("files", memberService.viewArticleInfo(seqno).getFileDTOs());
	}

	// 건강 정보 작성, 수정, 삭제 버튼은 thymeleaf에서 Session이 master면 보이도록 처리해줘야 한다.
	// 건강 정보 작성
	@GetMapping("/kids/writeNotice")
	public void getWriteAnnounce() {
	}

	@PostMapping("/kids/writeNotice")
	public String postAnnounce(ArticleDTO articleDTO,
			@RequestParam("fileUpload") List<MultipartFile> multipartFiles) {

		articleDTO.setType("announce");
		String path = "c:\\Repository\\file\\";

		List<FileDTO> fileDTOs = new ArrayList<>();

		File targetFile = null;

		for (MultipartFile multipartFile : multipartFiles) {
			String orgFilename = multipartFile.getOriginalFilename();
			String orgFileExtension = orgFilename.substring(orgFilename.lastIndexOf("."));
			String storedFilename = UUID.randomUUID().toString().replaceAll("-", "") + orgFileExtension;
			long fileSize = multipartFile.getSize();

			targetFile = new File(path + storedFilename);
			try {
				multipartFile.transferTo(targetFile);
			} catch (IllegalStateException | IOException e) {
				return "fail";
			}

			FileDTO fileDTO = FileDTO.builder().orgFilename(orgFilename).storedFilename(storedFilename)
					.fileSize(fileSize).build();
			fileDTOs.add(fileDTO);
		}

		memberService.writeArticleInfo(articleDTO, fileDTOs);
		return "success";

	}

	@GetMapping("/kids/notice/modify")
	public void getModifyAnnounce(@RequestParam("seqno") Long seqno, Model model) {
		model.addAttribute("article", memberService.viewArticleInfo(seqno).getArticleDTO());
		model.addAttribute("files", memberService.viewArticleInfo(seqno).getFileDTOs());
	}

	@PostMapping("/kids/notice/modify")
	public String postModifyAnnounce(ArticleDTO articleDTO,
			@RequestParam("fileUpload") List<MultipartFile> multipartFiles) {
		articleDTO.setType("announce");
		String path = "c:\\Repository\\file\\";

		List<FileDTO> fileDTOs = new ArrayList<>();

		File targetFile = null;

		for (MultipartFile multipartFile : multipartFiles) {
			String orgFilename = multipartFile.getOriginalFilename();
			String orgFileExtension = orgFilename.substring(orgFilename.lastIndexOf("."));
			String storedFilename = UUID.randomUUID().toString().replaceAll("-", "") + orgFileExtension;
			long fileSize = multipartFile.getSize();

			targetFile = new File(path + storedFilename);
			try {
				multipartFile.transferTo(targetFile);
			} catch (IllegalStateException | IOException e) {
				return "fail";
			}

			FileDTO fileDTO = FileDTO.builder().orgFilename(orgFilename).storedFilename(storedFilename)
					.fileSize(fileSize).build();
			fileDTOs.add(fileDTO);
		}

		memberService.modifyArticleInfo(articleDTO, fileDTOs);
		return "success";
	}

	@GetMapping("/kids/notice/delete")
	public void getAnnounceDelete(@RequestParam("seqno") Long seqno) {
		memberService.deleteArticleInfo(seqno);
	}

	//어드민 페이지 
	@GetMapping("/kids/admin")
	public void getAdminPage(){
	}

	//병원회원 허락 페이지 
	@GetMapping("/kids/admin/accept")
	public void getAccept(HospitalUserDTO hospitalUserDTO){
		
	}
}
