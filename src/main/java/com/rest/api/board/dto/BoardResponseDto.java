package com.rest.api.board.dto;

import com.rest.api.board.domain.Board;

import lombok.Getter;

@Getter
public class BoardResponseDto {

	private Long boardSeq;
	private String boardSubject;
	private String boardWriter;
	private String boardContent;

	public BoardResponseDto(Board board) {
		this.boardSeq = board.getBoardSeq();
		this.boardSubject = board.getBoardSubject();
		this.boardWriter = board.getBoardWriter();
		this.boardContent = board.getBoardContent();
	}
}
