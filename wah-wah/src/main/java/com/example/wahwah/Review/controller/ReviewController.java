package com.example.wahwah.Review.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.wahwah.Review.dto.ReviewDTO;
import com.example.wahwah.Review.dto.ReviewInterface;
import com.example.wahwah.Review.entity.ReviewEntity;
import com.example.wahwah.Review.service.ReviewService;
import com.example.wahwah.Review.entity.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewRepository reviewRepository;
    private final ReviewService service;

    @ResponseBody
    @PostMapping("/test/review")
    public List<ReviewInterface> getReview(ReviewInterface reply, @RequestParam("option") String option) {
        System.out.println("option = " + option);

        System.out.println("reply = " + reply.toString());

        switch(option) {
		
            case "I" : service.write(reply); //댓글 등록
                       break;
            case "U" : service.modify(reply); //댓글 수정
                       break;
            case "D" : service.delete(reply); //댓글 삭제
                       break;
            }
            
        return service.list(reply);
    }






}
