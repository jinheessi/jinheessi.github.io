package com.example.wahwah.member.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wahwah.member.entity.HospitalEntity;

public interface HospitalRepository extends JpaRepository<HospitalEntity,String>{
    
}

