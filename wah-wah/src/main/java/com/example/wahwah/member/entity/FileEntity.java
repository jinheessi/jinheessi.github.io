package com.example.wahwah.member.entity;

import com.example.wahwah.member.dto.FileDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "file")
@Table(name = "tbl_file")
@Getter
@Setter
@Builder
public class FileEntity {
    @Id
    @Column(name = "seqno")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tblFileSeqGenerator")
    @SequenceGenerator(sequenceName = "tblfileSeq", name = "tblFileSeqGenerator", allocationSize = 1)
    private Long seqno;

    @Column(name = "articleSeqno")
    private Long articleSeqno;

    @Column(name = "orgFilename")
    private String orgFilename;

    @Column(name = "storedFilename")
    private String storedFilename;

    @Column(name = "fileSize")
    private Long fileSize;

    public FileDTO entityToDto() {
        FileDTO fileDTO = FileDTO.builder().seqno(this.seqno).articleSeqno(this.articleSeqno)
                .orgFilename(this.orgFilename).storedFilename(this.storedFilename).build();

        return fileDTO;
    }
}
