package com.sample.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
