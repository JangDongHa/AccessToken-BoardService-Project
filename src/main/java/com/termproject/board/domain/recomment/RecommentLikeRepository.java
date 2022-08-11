package com.termproject.board.domain.recomment;

import com.termproject.board.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecommentLikeRepository extends JpaRepository<RecommentLike, Long> {
    Optional<List<RecommentLike>> findAllByUser(User user);

    List<RecommentLike>findByUserIdAndRecommentId(Long user, Long id );


    void deleteAllByUserIdAndRecommentId(Long user_id,Long recomment_id);
}
