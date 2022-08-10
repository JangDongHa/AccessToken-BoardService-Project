package com.termproject.board.service.impl;

import com.termproject.board.domain.board.Board;
import com.termproject.board.domain.board.BoardLike;
import com.termproject.board.domain.board.BoardLikeRepository;
import com.termproject.board.domain.board.BoardRepository;
import com.termproject.board.domain.user.User;
import com.termproject.board.domain.user.UserRepository;
import com.termproject.board.dto.ResponseBoardDto;
import com.termproject.board.dto.ResponseDto;
import com.termproject.board.exception.ExceptionNamingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardServiceImpl {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardLikeRepository boardLikeRepository;

    @Autowired
    private UserRepository userRepository;



    // 게시판 상세보기
    @Transactional(readOnly = true)
    public ResponseDto<ResponseBoardDto> getBoardDtoByboardId(long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(()->new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_BOARD));
        ResponseBoardDto dto = new ResponseBoardDto(board);
        return new ResponseDto<>(HttpStatus.OK, dto);
    }

    // 게시판 좋아요
    @Transactional
    public int likeBoardByBoardId(long boardId, String username){
        User user = userRepository.findByUsername(username).orElseThrow(()->new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_USERNAME));
        Board board = boardRepository.findById(boardId).orElseThrow(()->new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_BOARD));
        BoardLike boardLike = BoardLike.builder()
                        .board(board)
                                .user(user)
                                        .build();

        BoardLike boardLikePS = boardLikeRepository.findByBoardAndUser(board, user).orElse(null);
        
        if (boardLikePS == null){ // null = 한번도 해당 글에 좋아요를 누르지 않은 것
            board.setLikes(board.getLikes() + 1);
            boardLikeRepository.save(boardLike);

            boardRepository.save(board);            
        }



        return board.getLikes();
    }

    // 게시판 좋아요 취소
    @Transactional
    public int likeCancelBoardByBoardId(long boardId, String username){
        User user = userRepository.findByUsername(username).orElseThrow(()->new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_USERNAME));
        Board board = boardRepository.findById(boardId).orElseThrow(()->new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_BOARD));

        BoardLike boardLikePS = boardLikeRepository.findByBoardAndUser(board, user).orElse(null);
        if (boardLikePS != null){ // null = 한번도 해당 글에 좋아요를 누르지 않은 것
            board.setLikes(board.getLikes() - 1);
            boardLikeRepository.delete(boardLikePS);

            boardRepository.save(board);
        }
        

        return board.getLikes();
    }
}
