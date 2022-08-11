package com.termproject.board.controller;

import com.termproject.board.config.jwt.token.RequestToken;
import com.termproject.board.dto.*;
import com.termproject.board.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserApiController {
    @Autowired
    private UserServiceImpl userService;


    @PostMapping("/api/user/join")
    public ResponseDto<String> joinUser(@RequestBody RequestUserDto requestUserDto){
        return userService.joinUser(requestUserDto);
    }

    // 마이페이지 작성 게시글 조회
    @GetMapping("/api/user/boards") // /api/user/boards
    public List<ResponseBoardDto> getBoards(HttpServletRequest request){
        RequestToken requestToken = new RequestToken(request);
        String username = requestToken.getUsername().orElseThrow();

        return userService.getAllBoardsByUser(getUsernameByRequest(request));
    }

    // 마이페이지 좋아요한 게시글 조회
    @GetMapping("/api/user/like/boards") // /api/user/like/boards
    public List<ResponseBoardDto> getLikeBoards(HttpServletRequest request){
        return userService.getAllLikeBoardsByUser(getUsernameByRequest(request));
    }

    // 마이페이지 작성 댓글 조회
    @GetMapping("/api/user/comments") // /api/user/comments
    public List<ResponseCommentDto> getComments(HttpServletRequest request){
        return userService.getAllCommentsByUser(getUsernameByRequest(request));
    }

    // 마이페이지 좋아요한 댓글 조회
    @GetMapping("/api/user/like/comments") // /api/user/like/comments
    public List<ResponseCommentDto> getLikeComments(HttpServletRequest request){
        return userService.getAllLikeCommentsByUser(getUsernameByRequest(request));
    }

    // 마이페이지 작성 대댓글 조회
    @GetMapping("/api/user/recomments") // /api/user/recomments
    public List<ResponseRecommentDto> getRecomments(HttpServletRequest request){
        return userService.getAllRecommentByUser(getUsernameByRequest(request));
    }

    // 마이페이지 좋아요한 대댓글 조회
    @GetMapping("/api/user/like/recomments") // /api/user/like/recomments
    public List<ResponseRecommentDto> getLikeRecomments(HttpServletRequest request){
        return userService.getAllLikeRecommentByUser(getUsernameByRequest(request));
    }

    private String getUsernameByRequest(HttpServletRequest request){
        RequestToken requestToken = new RequestToken(request);
        return requestToken.getUsername().orElseThrow();
    }

    // RequestToken 을 이용하여 로그인정보를 알아내는 메서드 예시
    // 해당 메서드를 참조하여 로그인정보가 필요하면 RequestToken 에 getUsername 으로 받아내서 사용
    @GetMapping("/api/board/test")
    public String test(HttpServletRequest request){
        RequestToken requestToken = new RequestToken(request);
        String username = requestToken.getUsername().orElseThrow();

        return "<h1>"+username+"Hi</h1>";
    }
}
