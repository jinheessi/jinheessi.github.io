package com.example.wahwah.reservation.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.wahwah.reservation.dto.ReservationDTO;
import com.example.wahwah.reservation.dto.ReservationInterface;
import com.example.wahwah.reservation.entity.ReservationEntity;

public interface ReservationRepository extends JpaRepository<ReservationEntity,Integer>{
    

    // 예약 취소하기
    @Modifying
    @Query("update tbl_reservation set allow = 'N' where seqno = :seqno")
    public void cancel(@Param("seqno") int seqno);

    // 월+일 예약 조회하기
	@Query(value="SELECT * FROM tbl_reservation WHERE TO_CHAR(TO_DATE(reserv_start, 'YYYY-MM-DD HH24:MI:SS'), 'DD') = :day and TO_CHAR(TO_DATE(reserv_start, 'YYYY-MM-DD HH24:MI:SS'), 'MM') = :month and TO_CHAR(TO_DATE(reserv_start, 'YYYY-MM-DD HH24:MI:SS'), 'YYYY') = :year",nativeQuery=true)
	List<ReservationInterface> resList(@Param("year") String year ,@Param("day") String day, @Param("month") String month);

    
    // 월 예약 조회하기
	@Query(value="SELECT * FROM tbl_reservation WHERE TO_CHAR(TO_DATE(reserv_start, 'YYYY-MM-DD HH24:MI:SS'), 'MM') = :month and TO_CHAR(TO_DATE(reserv_start, 'YYYY-MM-DD HH24:MI:SS'), 'YYYY') = :year",nativeQuery=true)
	List<ReservationInterface> resMonthList(@Param("year") String year , @Param("month") String month);

    // 예약 정보 수정하기
    @Modifying
    @Query(value = "update tbl_reservation set user_info = :user_info, weight = :weight, height = :height, reserve_start = :reserve_start, birth = :birth where seqno = :seqno")
    public void reviceReservInfo(@Param("user_info") String user_info, @Param("weight") String weight, @Param("height") String height, @Param("reserve_start") String reserve_start, @Param("birth") String birth, @Param("seqno") int seqno);


    // 나의 예약 정보 가져오기
    @Query(value= "select * from tbl_reservation where email = :email", nativeQuery = true)
    public List<ReservationInterface> getMyrservationList(@Param("email") String email);

    // public List<ReservationInterface> findByEmail(String email);

}
