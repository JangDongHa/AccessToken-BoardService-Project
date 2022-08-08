package com.termproject.board.like.controller;


import com.termproject.board.dto.ResponseDto;
import com.termproject.board.like.service.impl.LikeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;


@RestController
public class LikeApiController {

    private final LikeServiceImpl likeService;


    public LikeApiController(LikeServiceImpl likeService){
        this.likeService = likeService;
    }

    @PostMapping("/api/auth/like/post/{id}")
    public ResponseDto<String> LikePost(@PathVariable Long id){



        return new ResponseDto<>(HttpStatus.OK,"좋아요 등록 완료");
    }


}
