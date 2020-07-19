package com.rest.api.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rest.api.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	@Query("SELECT p FROM Board p ORDER BY p.boardSeq DESC")
	List<Board> findAllDesc();
}