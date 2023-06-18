package com.example.wahwah.Review.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name="tbl_review")
@Table(name="tbl_review")
public class ReviewEntity {
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="review_seqno")
	@SequenceGenerator(name="review_seqno", sequenceName="review_seqno", initialValue=1, allocationSize=1)
    private Integer seqno;

    @Column(name="hospitalid")
    private String hospitalid;

    @Column(name="email")
    private String email;

    @Column(name="content")
    private String content;
    
    @Column(name="likecnt")
    private Integer likecnt;

    @Column(name="dislikecnt")
    private Integer dislikecnt;

    @Column(name="ranking")
    private String ranking;

}
