package com.termproject.board.domain.board;

import com.termproject.board.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    Optional<List<BoardLike>> findAllByUser(User user);
    Optional<BoardLike> findByBoardAndUser(Board board, User user);

     List<BoardLike> findByUserIdAndBoardId(long user, long id);
     void deleteAllByBoardIdAndUserId(long board_id, long user_id);
}
