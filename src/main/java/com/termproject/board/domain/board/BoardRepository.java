package com.termproject.board.domain.board;

import com.termproject.board.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByTitle(String title); // for test
}
