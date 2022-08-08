package com.termproject.board.like.service.impl;

import com.termproject.board.like.repository.LikeRepository;
import com.termproject.board.like.service.LikeService;


public class LikeServiceImpl {

    private final LikeRepository likeRepository;

    private LikeService likeService;

    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }
}
