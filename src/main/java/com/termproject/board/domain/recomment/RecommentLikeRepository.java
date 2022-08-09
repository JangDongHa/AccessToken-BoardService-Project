package com.termproject.board.domain.recomment;

import com.termproject.board.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecommentLikeRepository extends JpaRepository<RecommentLike, Long> {
    Optional<List<RecommentLike>> findAllByUser(User user);
}
