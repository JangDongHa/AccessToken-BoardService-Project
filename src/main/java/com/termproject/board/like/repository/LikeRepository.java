package com.termproject.board.like.repository;

import com.termproject.board.domain.user.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<User, Long> {


}
