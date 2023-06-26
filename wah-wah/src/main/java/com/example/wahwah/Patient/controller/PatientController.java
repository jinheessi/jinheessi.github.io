package com.example.wahwah.Patient.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.wahwah.Patient.dto.PatientDTO;
import com.example.wahwah.Patient.service.PatientService;
import com.example.wahwah.member.dto.MemberInterface;
import com.example.wahwah.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PatientController {
	
	private final MemberService mservice;
	private final PatientService pservice;
	
	@GetMapping("/kids/mybabycard")
	public void getMybabycard(HttpSession session, Model model) {
		
		MemberInterface member = mservice.memberInfoView((String)session.getAttribute("email"));
		String pname = (String)session.getAttribute("username");
		
		
		model.addAttribute("session", session);
		model.addAttribute("member", member);
		model.addAttribute("babies", pservice.myBabyCard(pname));
	}
	
	@GetMapping("/kids/viewbabycard")
	public void getViewbabycard(@RequestParam("bname") String bname, HttpSession session, Model model) {
		
		MemberInterface member = mservice.memberInfoView((String)session.getAttribute("email"));
		
		model.addAttribute("session", session);
		model.addAttribute("member", member);
		model.addAttribute("baby", pservice.viewBabyCard(bname));
	}
	
	
	@GetMapping("/kids/writebabycard")
	public void getWritebabycard(HttpSession session, Model model) {
		
		MemberInterface member = mservice.memberInfoView((String)session.getAttribute("email"));
		
		model.addAttribute("session", session);
		model.addAttribute("member", member);
	}
	
	@PostMapping("/kids/writebabycard")
	public String postWritebabycard(PatientDTO patient, @RequestParam("photo") MultipartFile mpr) throws IllegalStateException, IOException {
		String path = "c:\\Repository\\profile\\"; 
		
		if(!mpr.isEmpty()) {

			File targetFile = null; 
			String org_filename = mpr.getOriginalFilename();	
			String org_fileExtension = org_filename.substring(org_filename.lastIndexOf("."));	
			String stored_filename = UUID.randomUUID().toString().replaceAll("-", "") + org_fileExtension;	
			long filesize = mpr.getSize();
				
			targetFile = new File(path + stored_filename);
			mpr.transferTo(targetFile);
				
			patient.setOrg_filename(org_filename);
			patient.setStored_filename(stored_filename);
			patient.setFilesize(filesize);
		}
        
		pservice.writeBabyCard(patient);
		return "redirect:/kids/mybabycard";
	}
	
	@GetMapping("/kids/modifybabycard")
	public void getModifybabycard(@RequestParam("bname") String bname, HttpSession session, Model model) {
		
		MemberInterface member = mservice.memberInfoView((String)session.getAttribute("email"));
		model.addAttribute("session", session);
		model.addAttribute("member", member);
		model.addAttribute("baby", pservice.viewBabyCard(bname));
	}
	
	@PostMapping("/kids/modifybabycard")
	public String postModifybabycard(PatientDTO patient, @RequestParam("photo") MultipartFile mpr) throws IllegalStateException, IOException {
		
		String path = "c:\\Repository\\profile\\"; 
		
		if(!mpr.isEmpty()) {

			File targetFile = null; 
			String org_filename = mpr.getOriginalFilename();	
			String org_fileExtension = org_filename.substring(org_filename.lastIndexOf("."));	
			String stored_filename = UUID.randomUUID().toString().replaceAll("-", "") + org_fileExtension;	
			long filesize = mpr.getSize();
				
			targetFile = new File(path + stored_filename);
			mpr.transferTo(targetFile);
				
			patient.setOrg_filename(org_filename);
			patient.setStored_filename(stored_filename);
			patient.setFilesize(filesize);
		}
        
		pservice.modifyBabyCard(patient);
		return "redirect:/kids/mybabycard";
	}
	
	@GetMapping("/kids/deletebabycard")
	public String getDeletebabycard(@RequestParam("bname") String bname, HttpSession session, Model model) {
		
		MemberInterface member = mservice.memberInfoView((String)session.getAttribute("email"));
		
		model.addAttribute("session", session);
		model.addAttribute("member", member);
		
		pservice.deleteBabyCard(bname);
		return "redirect:/kids/mybabycard";
	}
	
	
	
}
