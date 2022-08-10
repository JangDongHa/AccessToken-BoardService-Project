package com.termproject.board.controller;

import com.termproject.board.dto.ResponseBoardDto;
import com.termproject.board.dto.ResponseDto;
import com.termproject.board.service.impl.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardApiController {
    @Autowired
    private BoardServiceImpl boardService;

    @GetMapping("/api/board/{boardId}")
    public ResponseDto<ResponseBoardDto> getBoardId(@PathVariable long boardId){
        return boardService.getBoardDtoById(boardId);
    }


}
