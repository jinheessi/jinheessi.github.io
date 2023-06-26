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

    // // 월+일 예약 조회하기
	// @Query(value="SELECT * FROM tbl_reservation WHERE TO_CHAR(TO_DATE(reservestart, 'YYYY-MM-DD HH24:MI:SS'), 'DD') = :day and TO_CHAR(TO_DATE(reservestart, 'YYYY-MM-DD HH24:MI:SS'), 'MM') = :month and TO_CHAR(TO_DATE(reservestart, 'YYYY-MM-DD HH24:MI:SS'), 'YYYY') = :year",nativeQuery=true)
	// List<ReservationInterface> resList(@Param("rdate") String rdate);

    // 월+일 예약 조회하기
    @Query(value = "SELECT * FROM tbl_reservation WHERE reservestart LIKE CONCAT('%', :rdate, '%')", nativeQuery = true)
    List<ReservationInterface> resList(@Param("rdate") String rdate);

    // 월 예약 조회하기
	@Query(value="SELECT * FROM tbl_reservation WHERE TO_CHAR(TO_DATE(reservestart, 'YYYY-MM-DD HH24:MI:SS'), 'MM') = :month and TO_CHAR(TO_DATE(reservestart, 'YYYY-MM-DD HH24:MI:SS'), 'YYYY') = :year",nativeQuery=true)
	List<ReservationEntity> resMonthList(@Param("year") String year , @Param("month") String month);

    // 예약 정보 수정하기
    @Modifying
    @Query(value = "update tbl_reservation set reservestart = :reservestart where seqno = :seqno")
    public void reviceReservInfo(@Param("reservestart") String reservestart, @Param("seqno") int seqno);


    // 나의 예약 정보들 가져오기
    @Query(value= "select * from tbl_reservation where email = :email", nativeQuery = true)
    public List<ReservationEntity> getMyrservationList(@Param("email") String email);

    public ReservationDTO findBySeqno(int seqno);

    // public List<ReservationInterface> findByEmail(String email);

}
