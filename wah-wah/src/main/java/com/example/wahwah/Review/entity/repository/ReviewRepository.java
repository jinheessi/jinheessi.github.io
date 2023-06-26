package com.example.wahwah.Review.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.wahwah.Review.dto.ReviewDTO;
import com.example.wahwah.Review.dto.ReviewInterface;
import com.example.wahwah.Review.entity.ReviewEntity;


public interface ReviewRepository extends JpaRepository<ReviewEntity,Integer> {

    // 리뷰 전체 가져오기
    @Query(value = "select * from tbl_review where hpid = :hpid order by seqno desc",nativeQuery=true)
    public List<ReviewInterface> reviewAll(@Param("hpid") String hpid);

    // 별점 전체 가져오기
    @Query(value = "select * from tbl_review where hpid = :hpid",nativeQuery=true)
    public List<ReviewEntity> allCountReview(@Param("hpid") String hpid);

    // // 별점 한개 추가하기
    // @Modifying
    // @Query(value="update tbl_review set likecnt = :likecnt where seqno=:seqno")
    // public ReviewEntity plusRank(@Param("seqno") int seqno,@Param("like") int likecnt);

    // // 별점 한개 취소하기
    // @Modifying
    // @Query(value="update tbl_review set dislikecnt = :dislikecnt where seqno=:seqno")
    // public ReviewEntity minusRank(@Param("seqno") int seqno,@Param("like") int dislikecnt);

}
