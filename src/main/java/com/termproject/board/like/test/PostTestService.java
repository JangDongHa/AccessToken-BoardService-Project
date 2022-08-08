package com.termproject.board.like.test;

public interface PostTestService {
    void post(PostTest postTest);

    PostTest findById(Long postTestId);
}
