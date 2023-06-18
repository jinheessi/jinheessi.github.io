package com.example.wahwah.member.dto;

import com.example.wahwah.member.entity.FileEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDTO {
    private Long seqno;
    private Long articleSeqno;
    private String orgFilename;
    private String storedFilename;
    private Long fileSize;

    public FileEntity dtoToEntity() {
        FileEntity fileEntity = FileEntity.builder().articleSeqno(this.articleSeqno).orgFilename(this.orgFilename)
                .storedFilename(this.storedFilename).fileSize(this.fileSize).build();
                
        return fileEntity;
    }
}
