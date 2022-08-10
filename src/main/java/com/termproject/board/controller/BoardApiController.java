package com.termproject.board.controller;

import com.termproject.board.config.jwt.token.RequestToken;
import com.termproject.board.config.jwt.token.ResponseToken;
import com.termproject.board.dto.*;
import com.termproject.board.service.impl.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/api/board")
    public ResponseDto<String> postBoard(@RequestBody RequestBoardDto dto, HttpServletRequest request){
        boardService.postBoard(dto, getUsernameByRequest(request));
        return new ResponseDto<>(HttpStatus.OK, "게시글 작성 완료");
    }

    @PostMapping("/api/board/{boardId}/comment")
    public ResponseDto<String> postComment(@RequestBody RequestCommentDto dto, @PathVariable long boardId, HttpServletRequest request){
        dto.setBoardId(boardId);
        boardService.postComment(dto, getUsernameByRequest(request));
        return new ResponseDto<>(HttpStatus.OK, String.format("%d번 게시글에 대한 댓글 작성 완료", dto.getBoardId()));
    }

    @PostMapping("/api/board/{boardId}/comment/{commentId}/Recomment")
    public ResponseDto<String> postRecomment(@RequestBody RequestRecommentDto dto, @PathVariable long commentId,  HttpServletRequest request){
        dto.setCommentId(commentId);
        boardService.postRecomment(dto, getUsernameByRequest(request));
        return new ResponseDto<>(HttpStatus.OK, String.format("%d번 댓글에 대한 대댓글 작성 완료", dto.getCommentId()));
    }


    private String getUsernameByRequest(HttpServletRequest request){
        RequestToken requestToken = new RequestToken(request);
        return requestToken.getUsername().orElseThrow();
    }




}
