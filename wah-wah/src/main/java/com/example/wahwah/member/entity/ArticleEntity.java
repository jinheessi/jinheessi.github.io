package com.example.wahwah.member.entity;

import com.example.wahwah.member.dto.ArticleDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "article")
@Table(name = "tbl_article")
@Getter
@Builder
public class ArticleEntity {
	@Id
	@Column(name = "seqno")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tblArticleSeqGenerator")
	@SequenceGenerator(sequenceName = "tblArticleSeq", name = "tblArticleSeqGenerator", allocationSize = 1)
	private Long seqno;

	// type은 announce(공지사항), posting(건강정보)로 나뉜다.
	@Column(name = "type")
	private String type;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "writer")
	private String writer;

	@Column(name = "regdate")
	private String regdate;

	public ArticleDTO entityToDto() {
		ArticleDTO articleDTO = ArticleDTO.builder().seqno(this.seqno).title(this.title).content(this.content)
				.writer(this.writer).regdate(this.regdate).build();
		return articleDTO;
	}
}
