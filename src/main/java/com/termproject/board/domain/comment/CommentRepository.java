package com.termproject.board.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findByBoardIdAndUserId(Long boardId,Long UserId);
}
