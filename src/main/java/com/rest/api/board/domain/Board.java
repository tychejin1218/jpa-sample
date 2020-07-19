package com.rest.api.board.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tbl_board")               
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_seq")
	private Long boardSeq;

	@Lob
	@Column(name = "board_subject")
	private String boardSubject;

	@Column(name = "board_writer")
	private String boardWriter;

	@Lob
	@Column(name = "board_content")
	private String boardContent;

	@Builder
	public Board(String boardSubject, String boardWriter, String boardContent) {
		this.boardSubject = boardSubject;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
	}

	public void update(String boardSubject, String boardContent) {
		this.boardSubject = boardSubject;
		this.boardContent = boardContent;
	}
}