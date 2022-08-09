package com.termproject.board.domain.comment;

import com.termproject.board.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<List<CommentLike>> findAllByUser(User user);
}
