package com.rest.api.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
