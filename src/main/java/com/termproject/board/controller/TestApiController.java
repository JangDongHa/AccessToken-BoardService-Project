package com.termproject.board.controller;

import com.termproject.board.config.jwt.token.RequestToken;
import com.termproject.board.domain.user.User;
import com.termproject.board.domain.user.UserRepository;
import com.termproject.board.dto.*;
import com.termproject.board.service.impl.BoardServiceImpl;
import com.termproject.board.service.impl.TestServiceImpl;
import com.termproject.board.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestApiController {
    @Autowired
    private BoardServiceImpl boardService;

    @Autowired
    private TestServiceImpl likeService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test/post")
    public ResponseDto<String> postAll(HttpServletRequest request){
        User user = userRepository.findByUsername(getUsernameByRequest(request)).orElseThrow();
        RequestBoardDto requestBoardDto = new RequestBoardDto("제목테스트", "내용테스트", user, "");
        RequestCommentDto requestCommentDto = new RequestCommentDto("코멘트내용테스트", user, 1);
        RequestRecommentDto requestRecommentDto = new RequestRecommentDto("리코멘트내용테스트", user, 1);

        boardService.postBoard(requestBoardDto, "jdh3340");
        boardService.postComment(requestCommentDto, "jdh3340");
        boardService.postRecomment(requestRecommentDto, "jdh3340");
        return new ResponseDto<>(HttpStatus.OK, "완료");
    }

    @GetMapping("/test/like")
    public ResponseDto<String> likeAll(HttpServletRequest request){
        User user = userRepository.findByUsername(getUsernameByRequest(request)).orElseThrow();
        likeService.likeBoard(1, user);
        likeService.likeComment(1, user);
        likeService.likeRecomment(1, user);
        return new ResponseDto<>(HttpStatus.OK, "완료");
    }


    @GetMapping("/test/delete")
    public ResponseDto<String> deleteTest(){
        boardService.deleteNoCommentBoards();
        return new ResponseDto<>(HttpStatus.OK, "삭제 완료");
    }

    private String getUsernameByRequest(HttpServletRequest request){
        RequestToken requestToken = new RequestToken(request);
        return requestToken.getUsername().orElseThrow();
    }
}
