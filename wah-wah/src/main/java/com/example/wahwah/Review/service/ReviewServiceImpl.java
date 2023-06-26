package com.example.wahwah.Review.service;
     
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.wahwah.Review.dto.ReviewDTO;
import com.example.wahwah.Review.dto.ReviewInterface;
import com.example.wahwah.Review.entity.ReviewEntity;
import com.example.wahwah.Review.entity.repository.ReviewRepository;
 
import lombok.RequiredArgsConstructor;
 
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    
    public final ReviewRepository repository;
    
    // 댓글등록
    @Override
    public void write(ReviewDTO review) {

        ReviewEntity reviewEntity = review.dtoToEntity(review);
        
        repository.save(reviewEntity);
    }

    // 댓글 수정
    @Override
    public void modify(ReviewInterface review) {
        
        ReviewEntity entity = repository.findById(review.getSeqno()).get();
        entity.setContent(review.getContent());
        repository.save(entity);
    }

    // 댓글 삭제
    @Override
    public void delete(ReviewInterface review) {
        ReviewEntity entity = repository.findById(review.getSeqno()).get();
        repository.deleteById(entity.getSeqno());
    }

    @Override
    public List<ReviewInterface> list(String hospitalid) {
        return repository.reviewAll(hospitalid);
    }

    // 별점 다 가져오기
    @Override
    public List<ReviewEntity> allCountReview(String hpid) {
        return repository.allCountReview(hpid);
    }

    // // 별점 한개 늘리기
    // @Override
    // public ReviewEntity plusRank(int seqno, int likecnt) {
    //     ReviewEntity entity = repository.findById(seqno).get();
    //     int like = entity.getLikecnt();
    //     like = like+1;
    //     return repository.plusRank(seqno, like);
    // }
    
    // // 별점 한개 빼기
    // @Override
    // public ReviewEntity minusRank(int seqno, int likecnt) {
    //     ReviewEntity entity = repository.findById(seqno).get();
    //     int like = entity.getLikecnt();
    //     like = like-1;
    //     return repository.minusRank(seqno, like);    
    // }



 

    
}



    
    // @Override
    // public List<ReviewDTO> list(ReviewDTO reviewDTO) {
    //     // System.out.println("reviewDTO = " + reviewDTO);
    //     // ReviewEntity entity = reviewDTO.dtoToEntity(reviewDTO);
    //     // System.out.println("entity = " + entity);
    //     // System.out.println("entity.getHosID = " + entity.getHospital_id());
    //     // return repository.reviewAll(entity.getHospital_id());
    //     return null;
    // }