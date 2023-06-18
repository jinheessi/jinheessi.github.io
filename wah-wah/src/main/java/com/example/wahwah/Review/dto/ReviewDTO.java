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
    private String hospitalid;
    private String email;
    private String content;
    private Integer likecnt;
    private Integer dislikecnt;
    private String ranking;


    public ReviewDTO(ReviewEntity entity) {
        this.seqno = entity.getSeqno();
        this.hospitalid = entity.getHospitalid();
        this.email = entity.getEmail();
        this.content = entity.getContent();
        this.likecnt = entity.getLikecnt();
        this.dislikecnt = entity.getDislikecnt();
        this.ranking = entity.getRanking();
    }



    //DTO --> Entity로 이동
    public ReviewEntity dtoToEntity(ReviewDTO dto) {
            
        ReviewEntity reviewEntity = ReviewEntity.builder()
                                        .seqno(dto.getSeqno())
                                        .hospitalid(dto.getHospitalid())
                                        .email(dto.getEmail())
                                        .content(dto.getContent())
                                        .likecnt(dto.getLikecnt())
                                        .dislikecnt(dto.getDislikecnt())
                                        .ranking(dto.getRanking())
                                        .build();
        return reviewEntity;
    }



}
