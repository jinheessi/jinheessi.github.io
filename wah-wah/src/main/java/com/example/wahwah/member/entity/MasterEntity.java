package com.example.wahwah.member.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="hos_master")
@Table(name="hos_master")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MasterEntity {
    

    @Id
    @Column(name="user_id")
    private String user_id;

    @Column(name="hospital_id")
    private String hospital_id;

    @Column(name="password")
    private String password;

    @Column(name="hospital_telno")
    private String hospital_telno;

    @Column(name="hospital_email")
    private String hospital_email;

    @Column(name = "supervisor")
    private String supervisor;

	
    

}
