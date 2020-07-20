package com.rest.api.board.dto;

import com.rest.api.board.domain.Board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {

	private String boardSubject;
	private String boardWriter;
	private String boardContent;

	@Builder
	public BoardSaveRequestDto(String boardSubject, String boardWriter, String boardContent) {
		this.boardSubject = boardSubject;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
	}

	public Board toEntity() {
		return Board.builder()
		            .boardSubject(boardSubject)
		            .boardWriter(boardWriter)
		            .boardContent(boardContent)
		            .build();
	}
}