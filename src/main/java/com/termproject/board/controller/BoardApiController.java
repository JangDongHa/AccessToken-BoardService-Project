package com.termproject.board.controller;

import com.termproject.board.config.jwt.token.RequestToken;
import com.termproject.board.config.jwt.token.ResponseToken;
import com.termproject.board.dto.ResponseBoardDto;
import com.termproject.board.dto.ResponseCommentDto;
import com.termproject.board.dto.ResponseDto;
import com.termproject.board.service.impl.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BoardApiController {
    @Autowired
    private BoardServiceImpl boardService;

    @GetMapping("/api/board/{boardId}")
    public ResponseDto<ResponseBoardDto> getBoard(@PathVariable long boardId){
        return boardService.getBoardDtoByboardId(boardId);
    }

    @GetMapping("/api/board/{boardId}/like")
    public ResponseDto<String> likeBoard(@PathVariable long boardId, HttpServletRequest request){
        int boardLikes = boardService.likeBoardByBoardId(boardId, getUsernameByRequest(request));
        return new ResponseDto<>(HttpStatus.OK, "좋아요 개수 : " + boardLikes);
    }

    @GetMapping("/api/board/{boardId}/like/cancel")
    public ResponseDto<String> likeCancelBoard(@PathVariable long boardId, HttpServletRequest request){
        int boardLikes = boardService.likeCancelBoardByBoardId(boardId, getUsernameByRequest(request));
        return new ResponseDto<>(HttpStatus.OK, "좋아요 개수 : " + boardLikes);
    }


    private String getUsernameByRequest(HttpServletRequest request){
        RequestToken requestToken = new RequestToken(request);
        return requestToken.getUsername().orElseThrow();
    }


}
