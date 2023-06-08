package com.example.wahwah.member.entity;

import org.glassfish.jaxb.core.v2.model.core.ID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity(name="user")
@Table(name="user")
@Builder
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    
    @Id
    @Column(name="user_id")
    private String user_id;


    @Column(name="password")
    private String password;

    @Column(name="user_name")
    private String user_name;

    @Column(name="email")
    private String email;

    @Column(name="telno")
    private String telno;

    @Column(name="address")
    private String address;

    @Column(name="birth")
    private String birth;

    @Column(name="gender")
    private String gender;

    @Column(name="weight")
    private String weight;

    @Column(name="height")
    private String height;

    @Column(name="user_info")
    private String user_info;

    @Column(name="role")
    private String role;
}
