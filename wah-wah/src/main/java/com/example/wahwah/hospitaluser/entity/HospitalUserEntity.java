package com.example.wahwah.hospitaluser.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "hospitaluser")
@Table(name = "tbl_hospitaluser")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HospitalUserEntity {
    @Id
    @Column(name = "hospitalUserId")
    private String hospitalUserId;

    @Column(name = "hospitalName")
    private String hospitalName;

    @Column(name = "hospitalAddr")
    private String hospitalAddr;

    @Column(name = "hpid")
    private String hpid;

    @Column(name = "hospitalPassword")
    private String password;

    @Column(name = "hospitalUserTelno")
    private String hospitalUserTelno;

    @Column(name = "hospitalEmail")
    private String hospitalEmail;

    @Column(name = "supervisor")
    private String supervisor;

    @Column(name = "hosptial_file")
    private String hosptial_file;

    @Column(name = "verified", columnDefinition = "varchar2(1 char) default 'N'")
    private String verified;

}
