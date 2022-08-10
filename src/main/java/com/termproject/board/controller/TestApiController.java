package com.termproject.board.controller;

import com.termproject.board.config.jwt.token.RequestToken;
import com.termproject.board.domain.user.User;
import com.termproject.board.domain.user.UserRepository;
import com.termproject.board.dto.*;
import com.termproject.board.service.impl.BoardServiceImpl;
import com.termproject.board.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TestApiController {
    @Autowired
    private BoardServiceImpl boardService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test/post")
    public ResponseDto<String> postAll(HttpServletRequest request){
        User user = getUser(request);
        RequestBoardDto requestBoardDto = new RequestBoardDto("제목테스트", "내용테스트", user);
        RequestCommentDto requestCommentDto = new RequestCommentDto("코멘트내용테스트", user, 1);
        RequestRecommentDto requestRecommentDto = new RequestRecommentDto("리코멘트내용테스트", user, 1);

        boardService.postBoard(requestBoardDto);
        boardService.postComment(requestCommentDto);
        boardService.postRecomment(requestRecommentDto);
        return new ResponseDto<>(HttpStatus.OK, "완료");
    }

    @GetMapping("/test/like")
    public ResponseDto<String> likeAll(HttpServletRequest request){
        User user = getUser(request);
        boardService.likeBoard(1, user);
        boardService.likeComment(1, user);
        boardService.likeRecomment(1, user);
        return new ResponseDto<>(HttpStatus.OK, "완료");
    }

    @GetMapping("/test/user/boards") // /api/user/boards
    public List<ResponseBoardDto> getBoards(HttpServletRequest request){
        RequestToken requestToken = new RequestToken(request);
        String username = requestToken.getUsername().orElseThrow(); // String 형을 반환을 해요
        User user = userRepository.findByUsername(username).orElseThrow(); // Users 객체를 반환을 해요

        return userService.getAllBoardsByUser(getUser(request));
    }

    @GetMapping("/test/user/like/boards") // /api/user/like/boards
    public List<ResponseBoardDto> getLikeBoards(HttpServletRequest request){
        return userService.getAllLikeBoardsByUser(getUser(request));
    }

    @GetMapping("/test/user/comments") // /api/user/comments
    public List<ResponseCommentDto> getComments(HttpServletRequest request){
        return userService.getAllCommentsByUser(getUser(request));
    }

    @GetMapping("/test/user/like/comments") // /api/user/like/comments
    public List<ResponseCommentDto> getLikeComments(HttpServletRequest request){
        return userService.getAllLikeCommentsByUser(getUser(request));
    }

    @GetMapping("/test/user/recomments") // /api/user/recomments
    public List<ResponseRecommentDto> getRecomments(HttpServletRequest request){
        return userService.getAllRecommentByUser(getUser(request));
    }

    @GetMapping("/test/user/like/recomments") // /api/user/like/recomments
    public List<ResponseRecommentDto> getLikeRecomments(HttpServletRequest request){
        return userService.getAllLikeRecommentByUser(getUser(request));
    }

    private User getUser(HttpServletRequest request){
        RequestToken requestToken = new RequestToken(request);
        String username = requestToken.getUsername().orElseThrow(()->new IllegalArgumentException("Can not find username"));
        return userRepository.findByUsername(username).orElseThrow();
    }
}
