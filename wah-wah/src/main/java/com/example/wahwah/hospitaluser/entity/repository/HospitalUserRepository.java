package com.example.wahwah.hospitaluser.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wahwah.hospitaluser.entity.HospitalUserEntity;
import java.util.List;


public interface HospitalUserRepository extends JpaRepository<HospitalUserEntity,String>{
    List<HospitalUserEntity> findByHospitalEmail(String hospitalEmail);

    List<HospitalUserEntity> findByHospitalEmailAndHospitalUserTelno(String hospitalEmail, String hospitalUserTelno);
}
