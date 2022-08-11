package com.termproject.board.service.impl;

import com.termproject.board.domain.board.Board;
import com.termproject.board.domain.board.BoardLike;
import com.termproject.board.domain.board.BoardLikeRepository;
import com.termproject.board.domain.board.BoardRepository;
import com.termproject.board.domain.comment.Comment;
import com.termproject.board.domain.comment.CommentRepository;
import com.termproject.board.domain.recomment.RecommentRepository;
import com.termproject.board.domain.user.User;
import com.termproject.board.domain.user.UserRepository;
import com.termproject.board.dto.*;
import com.termproject.board.exception.ExceptionNamingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardLikeRepository boardLikeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RecommentRepository recommentRepository;



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

    @Transactional
    public void postBoard(RequestBoardDto dto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_USERNAME));
        dto.setUser(user);
        boardRepository.save(dto.toBoard());
    }

    @Transactional
    public void postComment(RequestCommentDto dto, String username){
        User user = userRepository.findByUsername(username).orElseThrow(()->new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_USERNAME));
        dto.setUser(user);
        Board board = boardRepository
                .findById(dto.getBoardId())
                .orElseThrow(()->new IllegalArgumentException("Can not find board"));
        commentRepository.save(dto.toComment(board));

    }

    @Transactional
    public void postRecomment(RequestRecommentDto dto, String username){
        User user = userRepository.findByUsername(username).orElseThrow(()->new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_USERNAME));
        dto.setUser(user);
        Comment comment = commentRepository
                .findById(dto.getCommentId())
                .orElseThrow(()->new IllegalArgumentException("Can not find comment"));
        recommentRepository.save(dto.toRecomment(comment));
    }

    @Transactional(readOnly = true)
    public List<ResponseBoardDto> getAllBoardDto(){
        List<Board> boardPS = boardRepository.findAll();
        List<ResponseBoardDto> reponseBoards = new ArrayList<>();

        boardPS.forEach(board -> reponseBoards.add(new ResponseBoardDto(board)));
        return reponseBoards;
    }

    @Transactional
    public void deleteNoCommentBoards(){
        List<Board> boards = boardRepository.findAll();
        boards.forEach(board -> {
            if (board.getComments().size() == 0)
                boardRepository.delete(board);
        });
    }

}
