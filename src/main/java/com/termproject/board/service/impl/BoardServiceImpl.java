package com.termproject.board.service.impl;

import com.termproject.board.domain.board.Board;
import com.termproject.board.domain.board.BoardRepository;
import com.termproject.board.dto.ResponseBoardDto;
import com.termproject.board.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl {
    @Autowired
    private BoardRepository boardRepository;


    public ResponseDto<ResponseBoardDto> getBoardDtoById(long id){
        Board board = boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Can not find board id"));
        ResponseBoardDto dto = new ResponseBoardDto(board);
        return new ResponseDto<>(HttpStatus.OK, dto);
    }
}
