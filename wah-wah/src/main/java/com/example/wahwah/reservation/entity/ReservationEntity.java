package com.example.wahwah.reservation.entity;

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

@Entity(name="tbl_reservation")
@Table(name="tbl_reservation")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
    name="RESERVATION_SEQ_GENERATOR",
    sequenceName = "RESERVATION_SEQ",
    initialValue = 1, allocationSize = 1
)
    public class ReservationEntity {
    @Column(name="seqno")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESERVATION_SEQ_GENERATOR")
    int seqno;

    @Column(name="allow")
    String allow;

    @Column(name="email")
    String email;

    @Column(name="hpid")
    String hpid;

    @Column(name="reserve_start")
    String reserve_start;

    @Column(name="birth")
    String birth;

    @Column(name="gender")
    String gender;

    @Column(name="weight")
    String weight;

    @Column(name="height")
    String height;

    @Column(name="user_info")
    String user_info;

    @Column(name="regdate")
    String regdate;

    @Column(name="month")
    String month;

    @Column(name="day")
    String day;
}
