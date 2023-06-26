package com.example.wahwah.Review.service;

import java.util.List;

import com.example.wahwah.Review.dto.ReviewDTO;
import com.example.wahwah.Review.dto.ReviewInterface;
import com.example.wahwah.Review.entity.ReviewEntity;

public interface ReviewService {
     
    // 댓글 달기
    public void write(ReviewDTO reviewDTO);

    // 댓글 수정
    public void modify(ReviewInterface reviewInterface);

    // 댓글 삭제
    public void delete(ReviewInterface reviewInterface);
    
    // 댓글 목록
    public List<ReviewInterface> list(String hospitalid);

    // 별점 다 가져오기
    public List<ReviewEntity> allCountReview(String hpid);

    // // 별점 한개 늘리기
    // public ReviewEntity plusRank(int seqno, int likecnt);
    
    // // 별점 한개 취소하기
    // public ReviewEntity minusRank(int seqno, int likecnt);
}
