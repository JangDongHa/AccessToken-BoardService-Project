package com.termproject.board.like.repository;

import com.termproject.board.domain.user.User;
import com.termproject.board.like.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<User, Long> {

    void sum(Like like);

    void sub(Like like);

}
