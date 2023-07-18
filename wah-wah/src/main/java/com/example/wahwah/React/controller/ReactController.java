package com.example.wahwah.React.controller;

import java.util.*;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.wahwah.member.dto.MemberInterface;
import com.example.wahwah.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReactController {
	
	private final MemberService mservice;
	
	@GetMapping("/api/session")
	public Map<String, String> getLogin(HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("email", (String)session.getAttribute("email"));
		map.put("username", (String)session.getAttribute("username"));
		return map;
	}
	
	@GetMapping("/api/member")
	public MemberInterface getMember(HttpSession session){
		MemberInterface member = mservice.memberInfoView((String)session.getAttribute("email"));
		
		return member;
	}
}
