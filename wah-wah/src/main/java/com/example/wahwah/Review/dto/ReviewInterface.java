package com.example.wahwah.Review.dto;

public interface ReviewInterface {

    Integer getSeqno();
    String getHospitalid();
    String getEmail();
    String getContent();
    Integer getLikecnt();
    Integer getDislikecnt();
    String getRanking();
    String getRegdate();

}
