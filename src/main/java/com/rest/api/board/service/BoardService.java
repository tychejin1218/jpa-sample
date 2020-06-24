package com.rest.api.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.board.domain.Board;
import com.rest.api.board.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	/**
	 * 게시글 - 목록 조회
	 * 
	 * @return boards
	 */
	public List<Board> getBoardList() {

		List<Board> boards = new ArrayList<>();

		boardRepository.findAll()
		               .forEach(e -> boards.add(e));

		return boards;
	}

	/**
	 * 게시글 - 상세 조회
	 * 
	 * @param boardSeq
	 * @return
	 */
	public Board getBoard(Long boardSeq) {

		Board board = new Board();

		Optional<Board> boardOptional = boardRepository.findById(boardSeq);
		if (boardOptional.isPresent()) {
			board = boardOptional.get();
		}

		return board;
	}

	/**
	 * 게시글 - 저장
	 *
	 * @param board
	 */
	public Board insertBoard(Board insertBoard) {

		return boardRepository.save(insertBoard);
	}

	/**
	 * 게시글 - 수정
	 * 
	 * @param boardSeq
	 * @param board
	 */
	public Board updateBoard(Long boardSeq, Board updateBoard) {

		Board board = new Board();

		Optional<Board> boardOptional = boardRepository.findById(boardSeq);
		if (boardOptional.isPresent()) {
			board = boardOptional.get();
		}

		board.updateBoard(updateBoard);

		return boardRepository.save(board);
	}

	/**
	 * 게시글 - 삭제
	 * 
	 * @param boardSeq
	 */
	public void deleteBoard(Long boardSeq) {

		boardRepository.deleteById(boardSeq);
	}
}