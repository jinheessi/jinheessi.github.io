package com.example.wahwah.Receipt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.wahwah.Hospital.dto.HospitalDTO;
import com.example.wahwah.Receipt.dto.ReceiptDTO;
import com.example.wahwah.Receipt.entity.ReceiptEntity;
import com.example.wahwah.Receipt.service.ReceiptService;
import com.example.wahwah.member.dto.MemberDTO;
import com.example.wahwah.member.dto.MemberInterface;
import com.example.wahwah.member.service.MemberService;
import com.example.wahwah.Receipt.dto.*;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReceiptController {
	
	private final MemberService mservice;
	private final ReceiptService rservice;
	
    // 접수하기
    @GetMapping("/kids/receipt")
    public void getReceipt(@RequestParam("hpid") String hpid, @RequestParam("dutyName") String dutyName, HttpSession session, Model model) {
    	
    	MemberInterface member = mservice.memberInfoView(String.valueOf(session.getAttribute("email")));
    	System.out.println(member.getEmail());
    	
    	model.addAttribute("member", member);
    	model.addAttribute("session", session);
    	model.addAttribute("hpid", hpid);
    	model.addAttribute("dutyName", dutyName);
    }
    
    @PostMapping("/kids/receipt")
    public String postReceipt(ReceiptDTO receiptDTO) {
    	rservice.receipt(receiptDTO);
    	return "redirect:/kids/myreceipt";
    }
    
    
    // 내 접수 관리 화면 보여주기
    @GetMapping("/kids/myreceipt")
    public void getMyReceipt(HttpSession session, Model model) {
    	MemberInterface member = mservice.memberInfoView((String)session.getAttribute("email"));
    	List<ReceiptInterface> receiptList = rservice.receiptList((String) session.getAttribute("username"));
    	
   
    	model.addAttribute("member", member);
    	model.addAttribute("session", session);
    	model.addAttribute("receiptList", receiptList);
    }
    
    // 접수 수정
    @GetMapping("/kids/modifyReceipt")
    public void getMyModifyReceipt(@RequestParam("seqno") int seqno, HttpSession session, Model model) {
    	
    	model.addAttribute("session", session);
    	model.addAttribute("member", mservice.memberInfoView((String)session.getAttribute("email")));
    	model.addAttribute("receiptList", rservice.receiptView(seqno));
    }
    @PostMapping("/kids/modifyReceipt")
    public void postMyModifyReceipt(ReceiptDTO receiptDTO) {
    	rservice.receiptUpdate(receiptDTO);
    }
    
    // 접수 취소
    @GetMapping("/kids/deleteReceipt")
    public String getDeleteReceipt(@RequestParam("seqno") int seqno) {
    	
    	rservice.receiptDelete(seqno);
    	return "redirect:/kids/myreceipt";
    	
    }
}
