package com.rest.api.board.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.api.board.domain.Board;
import com.rest.api.board.dto.BoardSaveRequestDto;
import com.rest.api.board.dto.BoardUpdateRequestDto;
import com.rest.api.board.repository.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private BoardRepository boardRepository;

	@Test
	public void findAll() throws Exception {

		String url = "http://localhost:" + port + "/api/v1/board";

		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).contains("boardSeq");
		assertThat(responseEntity.getBody()).contains("boardSubject");
		assertThat(responseEntity.getBody()).contains("boardWriter");
		assertThat(responseEntity.getBody()).contains("boardContent");
	}

	@Test
	public void findById() throws Exception {

		Long boardSeq = 1L;

		String url = "http://localhost:" + port + "/api/v1/board/" + boardSeq;

		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).contains("boardSeq");
		assertThat(responseEntity.getBody()).contains("boardSubject");
		assertThat(responseEntity.getBody()).contains("boardWriter");
		assertThat(responseEntity.getBody()).contains("boardContent");
	}

	@Test
	public void save() throws Exception {

		String boardSubject = "게시글_제목_저장_01";
		String boardWriter = "게시글_작성자_저장_01";
		String boardContent = "게시글_내용_저장_01";

		BoardSaveRequestDto boardSaveRequestDto = BoardSaveRequestDto.builder()
		                                                             .boardSubject(boardSubject)
		                                                             .boardWriter(boardWriter)
		                                                             .boardContent(boardContent)
		                                                             .build();

		String url = "http://localhost:" + port + "/api/v1/board";

		ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, boardSaveRequestDto, Long.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(responseEntity.getBody()).isGreaterThan(0L);

		List<Board> boardList = boardRepository.findAll();
		Board board = boardList.get(boardList.size() - 1);
		assertThat(board.getBoardSubject()).isEqualTo(boardSubject);
		assertThat(board.getBoardWriter()).isEqualTo(boardWriter);
		assertThat(board.getBoardContent()).isEqualTo(boardContent);
	}

	@Test
	public void update() throws Exception {

		String boardSubject = "게시글_제목_저장_01";
		String boardWriter = "게시글_작성자_저장_01";
		String boardContent = "게시글_내용_저장_01";

		Board saveBoard = boardRepository.save(Board.builder()
		                                            .boardSubject(boardSubject)
		                                            .boardWriter(boardWriter)
		                                            .boardContent(boardContent)
		                                            .build());

		Long boardSeq = saveBoard.getBoardSeq();
		String updatedBoardSubject = "게시글_제목_수정_01";
		String updatedBoardContent = "게시글_내용_수정_01";

		BoardUpdateRequestDto boardUpdateRequestDto = BoardUpdateRequestDto.builder()
		                                                                   .boardSubject(updatedBoardSubject)
		                                                                   .boardContent(updatedBoardContent)
		                                                                   .build();

		String url = "http://localhost:" + port + "/api/v1/board/" + boardSeq;

		HttpEntity<BoardUpdateRequestDto> requestEntity = new HttpEntity<>(boardUpdateRequestDto);

		ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(responseEntity.getBody()).isGreaterThan(0L);

		List<Board> boardList = boardRepository.findAll();
		Board board = boardList.get(boardList.size() - 1);
		assertThat(board.getBoardSubject()).isEqualTo(updatedBoardSubject);
		assertThat(board.getBoardContent()).isEqualTo(updatedBoardContent);
	}

	@Test
	public void delete() throws Exception {

		String boardSubject = "게시글_제목_저장_01";
		String boardWriter = "게시글_작성자_저장_01";
		String boardContent = "게시글_내용_저장_01";

		Board saveBoard = boardRepository.save(Board.builder()
		                                            .boardSubject(boardSubject)
		                                            .boardWriter(boardWriter)
		                                            .boardContent(boardContent)
		                                            .build());

		Long boardSeq = saveBoard.getBoardSeq();

		String url = "http://localhost:" + port + "/api/v1/board/" + boardSeq;

		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);

		ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Long.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
}
