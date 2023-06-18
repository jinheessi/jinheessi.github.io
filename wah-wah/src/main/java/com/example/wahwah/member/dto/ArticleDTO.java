package com.example.wahwah.member.dto;

import java.time.LocalDateTime;

import com.example.wahwah.member.entity.ArticleEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleDTO {
    private Long seqno;
    // type은 announce(공지사항), posting(건강정보)로 나뉜다.
    private String type;
    private String title;
    private String content;
    private String writer;
    private String regdate;

    public ArticleEntity dtoToEntity() {
        ArticleEntity articleEntity = ArticleEntity.builder().type(this.type).title(this.title).content(this.content)
                .writer(this.writer).regdate(LocalDateTime.now().toString()).build();

        return articleEntity;
    }

}
