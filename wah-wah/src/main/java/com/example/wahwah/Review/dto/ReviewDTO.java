package com.example.wahwah.Review.dto;

import com.example.wahwah.Review.entity.ReviewEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReviewDTO {
    //
    private Integer seqno;
    private String dutyName;
    private String hpid;
    private String writer;
    private String email;
    private String regdate;
    private String ranking;
    private String title;
    private String content;
   

    public ReviewDTO(ReviewEntity entity) {
        this.seqno = entity.getSeqno();
        this.dutyName = entity.getDutyName();
        this.hpid = entity.getHpid();
        this.writer = entity.getWriter();
        this.email = entity.getEmail();
        this.regdate = entity.getRegdate();
        this.ranking = entity.getRanking();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }



    //DTO --> Entity로 이동
    public ReviewEntity dtoToEntity(ReviewDTO dto) {
            
        ReviewEntity reviewEntity = ReviewEntity.builder()
                                        .seqno(dto.getSeqno())
                                        .dutyName(dto.getDutyName())
                                        .hpid(dto.getHpid())
                                        .writer(dto.getWriter())
                                        .email(dto.getEmail())
                                        .regdate(dto.getRegdate())
                                        .ranking(dto.getRanking())
                                        .title(dto.getTitle())
                                        .content(dto.getContent())                                        
                                        .build();
        return reviewEntity;
    }



}
