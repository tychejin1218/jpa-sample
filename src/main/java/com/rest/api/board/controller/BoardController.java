package com.rest.api.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.board.domain.Board;
import com.rest.api.board.service.BoardService;

@RequestMapping("/v1/boards")
@RestController
public class BoardController {

	@Autowired
	private BoardService boardService;

	/**
	 * 게시글 - 목록 조회
	 * 
	 * @return
	 */
	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Board>> getBoardList() {

		List<Board> boardList = boardService.getBoardList();

		return new ResponseEntity<List<Board>>(boardList, HttpStatus.OK);
	}

	/**
	 * 게시글 - 상세 조회
	 * 
	 * @param boardSeq
	 * @return
	 */
	@GetMapping(value = "/{boardSeq}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Board> getBoard(@PathVariable("boardSeq") Long boardSeq) {

		Board board = boardService.getBoard(boardSeq);

		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}

	/**
	 * 게시글 - 저장
	 * 
	 * @param insertBoard
	 * @return
	 */
	@PostMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Board> insertBoard(@RequestBody Board insertBoard) {

		Board board = boardService.insertBoard(insertBoard);

		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}

	/**
	 * 게시글 - 수정
	 * 
	 * @param boardSeq
	 * @param updateBoard
	 * @return
	 */
	@PutMapping(value = "/{boardSeq}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Board> updateBoard(@PathVariable("boardSeq") Long boardSeq, @RequestBody Board updateBoard) {

		Board board = boardService.updateBoard(boardSeq, updateBoard);

		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}

	/**
	 * 게시글 - 삭제
	 * 
	 * @param boardSeq
	 * @return
	 */
	@DeleteMapping(value = "/{boardSeq}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> deleteBoard(@PathVariable("boardSeq") Long boardSeq) {

		boardService.deleteBoard(boardSeq);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
