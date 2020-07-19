package com.rest.api.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.api.board.domain.Board;
import com.rest.api.board.dto.BoardInsertRequestDto;
import com.rest.api.board.dto.BoardResponseDto;
import com.rest.api.board.dto.BoardUpdateRequestDto;
import com.rest.api.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;

	/** 게시글 - 목록 조회 */
	@Transactional(readOnly = true)
	public List<BoardResponseDto> findAll() {
		
		return boardRepository.findAll()
		                      .stream()
		                      .map(BoardResponseDto::new)
		                      .collect(Collectors.toList());
	}

	/** 게시글 - 상세 조회 */
	@Transactional(readOnly = true)
	public BoardResponseDto findById(Long boardSeq) {

		Board board = boardRepository.findById(boardSeq)
		                             .orElseThrow(() -> new IllegalAccessError("[boardSeq=" + boardSeq + "] 해당 게시글이 존재하지 않습니다."));

		return new BoardResponseDto(board);
	}

	/** 게시글 - 저장 */
	@Transactional
	public Long save(BoardInsertRequestDto boardInsertRequestDto) {

		return boardRepository.save(boardInsertRequestDto.toEntity())
		                      .getBoardSeq();
	}

	/** 게시글 - 수정 */
	@Transactional
	public Long update(Long boardSeq, BoardUpdateRequestDto boardUpdateRequestDto) {

		Board board = boardRepository.findById(boardSeq)
		                             .orElseThrow(() -> new IllegalAccessError("[boardSeq=" + boardSeq + "] 해당 게시글이 존재하지 않습니다."));

		board.update(boardUpdateRequestDto.getBoardSubject(), boardUpdateRequestDto.getBoardContent());

		return boardSeq;
	}

	/** 게시글 - 삭제 */
	@Transactional
	public void delete(Long boardSeq) {

		Board board = boardRepository.findById(boardSeq)
		                             .orElseThrow(() -> new IllegalAccessError("[boardSeq=" + boardSeq + "] 해당 게시글이 존재하지 않습니다."));

		boardRepository.delete(board);
	}
}