package com.termproject.board.like.test;

public interface PostTestRepository {

    void save(PostTest postTest);

    PostTest findById(Long postTestId);
}
