package com.termproject.board.controller;

import com.termproject.board.dto.ResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class BoardApiController {
    @GetMapping("/api/board/{boardId}")
    public ResponseDto<String> getBoardId(@PathVariable long boardId){
        return null;
    }
}
