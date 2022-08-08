package com.termproject.board.like.service;


import com.termproject.board.like.repository.LikeRepository;
import com.termproject.board.like.test.PostTestRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private final PostTestRepository postTestRepository;
    public LikeService(PostTestRepository postTestRepository) {
        this.postTestRepository = postTestRepository;
    }




    private  Long likes;

    void sum(Long likes){ this.likes = likes;}


}
