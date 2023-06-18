package com.example.wahwah.member.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wahwah.member.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long>{
    //게시물의 번호로 가져오는 파일들 
    public List<FileEntity> findByArticleSeqno(Long article_seqno);
    public void deleteByArticleSeqno(Long article_seqno);
}
