package com.rest.api.board.controller;

import java.util.List;

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

import com.rest.api.board.dto.BoardSaveRequestDto;
import com.rest.api.board.dto.BoardResponseDto;
import com.rest.api.board.dto.BoardUpdateRequestDto;
import com.rest.api.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
@RestController
public class BoardController {

	private final BoardService boardService;

	/** 게시글 - 목록 조회  */
	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<BoardResponseDto>> findAll() {

		List<BoardResponseDto> boardResponseDtoList = boardService.findAll();

		return new ResponseEntity<List<BoardResponseDto>>(boardResponseDtoList, HttpStatus.OK);
	}

	/** 게시글 - 상세 조회 */
	@GetMapping(value = "/{boardSeq}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BoardResponseDto> findById(@PathVariable("boardSeq") Long boardSeq) {

		BoardResponseDto boardResponseDto = boardService.findById(boardSeq);

		return new ResponseEntity<BoardResponseDto>(boardResponseDto, HttpStatus.OK);
	}

	/** 게시글 - 등록 */
	@PostMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Long> save(@RequestBody BoardSaveRequestDto boardSaveRequestDto) {

		Long savedBoardSeq = boardService.save(boardSaveRequestDto);

		return new ResponseEntity<Long>(savedBoardSeq, HttpStatus.CREATED);
	}

	/** 게시글 - 수정 */
	@PutMapping(value = "/{boardSeq}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Long> update(@PathVariable("boardSeq") Long boardSeq, @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {

		Long updatedboardSeq = boardService.update(boardSeq, boardUpdateRequestDto);

		return new ResponseEntity<Long>(updatedboardSeq, HttpStatus.CREATED);
	}

	/** 게시글 - 삭제 */
	@DeleteMapping(value = "/{boardSeq}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Long> delete(@PathVariable("boardSeq") Long boardSeq) {

		boardService.delete(boardSeq);

		return new ResponseEntity<Long>(boardSeq, HttpStatus.NO_CONTENT);
	}
}