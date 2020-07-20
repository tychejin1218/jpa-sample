package com.rest.api.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardUpdateRequestDto {

	private String boardSubject;
	private String boardContent;

	@Builder
	public BoardUpdateRequestDto(String boardSubject, String boardContent) {
		this.boardSubject = boardSubject;
		this.boardContent = boardContent;
	}
}