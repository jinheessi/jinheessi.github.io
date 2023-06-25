package com.example.wahwah.Review.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.wahwah.Hospital.dto.HospitalDTO;
import com.example.wahwah.Hospital.service.HospitalService;
import com.example.wahwah.Review.dto.ReviewDTO;
import com.example.wahwah.Review.dto.ReviewInterface;
import com.example.wahwah.Review.entity.ReviewEntity;
import com.example.wahwah.Review.service.ReviewService;
import com.example.wahwah.member.dto.MemberDTO;
import com.example.wahwah.member.dto.MemberInterface;
import com.example.wahwah.member.service.MemberService;

import jakarta.servlet.http.HttpSession;

import com.example.wahwah.Review.entity.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewRepository reviewRepository;
    private final ReviewService rservice;
    private final MemberService mservice;
    private final HospitalService hservice;
    
    
    @GetMapping("/kids/writeReview")
    public void getWriteReview(HospitalDTO hospitalDTO, HttpSession session, Model model) {
    	MemberInterface member = mservice.memberInfoView(String.valueOf(session.getAttribute("email")));
    	model.addAttribute("email", session.getAttribute("email"));
        
        
        model.addAttribute("member", member);
        model.addAttribute("session", session);
    	model.addAttribute("hospital", hospitalDTO);
    }
    
    @PostMapping("/kids/writeReview")
    public String postWriteReview(ReviewDTO reviewDTO) {
    	rservice.write(reviewDTO);
    	return "redirect:/kids/reviewView";
    }
    
    @GetMapping("/kids/reviewView")
    public void getReviewView(HospitalDTO hospitalDTO, HttpSession session, Model model) {
    	
    	MemberInterface member = mservice.memberInfoView((String)session.getAttribute("email"));
    	List<ReviewInterface> reviewList = rservice.list(hospitalDTO.getHpid());
    	System.out.println(reviewList);    	
    	
    	model.addAttribute("dutyName", hospitalDTO.getDutyName());
    	model.addAttribute("hpid", hospitalDTO.getHpid());
    	model.addAttribute("reviewList", reviewList);
    	model.addAttribute("member", member);
    	model.addAttribute("session", session);
    	
    }
    
    @GetMapping("/kids/write")
    public void getWrite(HospitalDTO hospitalDTO, Model model) {
    	HospitalDTO hospital = hservice.info(hospitalDTO.getHpid());
    	
    	model.addAttribute("hospital", hospital);
    }

//    @ResponseBody
//    @PostMapping("/test/review")
//    public List<ReviewInterface> getReview(ReviewInterface reply, @RequestParam("option") String option) {
//        System.out.println("option = " + option);
//
//        System.out.println("reply = " + reply.toString());
//
//        switch(option) {
//		
//            case "I" : rservice.write(reply); //댓글 등록
//                       break;
//            case "U" : rservice.modify(reply); //댓글 수정
//                       break;
//            case "D" : rservice.delete(reply); //댓글 삭제
//                       break;
//            }
//            
//        return rservice.list(reply);
    }

