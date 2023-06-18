package com.example.wahwah.member.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ArticleAndFile {
    private ArticleDTO articleDTO;
    private List<FileDTO> fileDTOs;
}
