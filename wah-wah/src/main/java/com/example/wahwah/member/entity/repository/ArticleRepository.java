package com.example.wahwah.member.entity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wahwah.member.entity.ArticleEntity;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
	Page<ArticleEntity> findByType(String type, Pageable pageable);
    
}