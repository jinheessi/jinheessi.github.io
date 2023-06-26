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
@SequenceGenerator(
	    name="REVIEW_SEQ_GENERATOR",
	    sequenceName = "REVIEW_SEQ",
	    initialValue = 1, allocationSize = 1
)
public class ReviewEntity {
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="REVIEW_SEQ_GENERATOR")
    private Integer seqno;
    
    @Column(name="dutyName")
    private String dutyName;
    
    @Column(name="hpid")
    private String hpid;
    
    @Column(name="writer")
    private String writer;

    @Column(name="email")
    private String email;
    
    @Column(name="regdate")
    private String regdate;
    
    @Column(name="ranking")
    private String ranking;
    
    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

   

}
