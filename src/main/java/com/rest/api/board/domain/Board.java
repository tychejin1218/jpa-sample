package com.rest.api.board.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Entity(name = "TBL_BOARD")
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_seq")
	private Long boardSeq;

	@Column(name = "board_writer", length = 20)
	private String boardWriter;

	@Lob
	@Column(name = "board_subject")
	private String boardSubject;

	@Lob
	@Column(name = "board_content")
	private String boardContent;

	public Board() {
	}

	public Board(Long boardSeq) {
		this.boardSeq = boardSeq;
	}
	
	public void updateBoard(Board updateBoard) {
		this.boardSubject = updateBoard.boardSubject;
		this.boardContent = updateBoard.boardContent;
	}
}