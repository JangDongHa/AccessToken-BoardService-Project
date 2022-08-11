package com.termproject.board.service.impl;

import com.termproject.board.config.jwt.token.RequestToken;
import com.termproject.board.domain.board.Board;
import com.termproject.board.domain.board.BoardLike;
import com.termproject.board.domain.board.BoardLikeRepository;
import com.termproject.board.domain.board.BoardRepository;
import com.termproject.board.domain.comment.Comment;
import com.termproject.board.domain.comment.CommentRepository;
import com.termproject.board.domain.recomment.Recomment;
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
    public ResponseDto<ResponseBoardDto> getBoardDtoByboardId(long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_BOARD));
        ResponseBoardDto dto = new ResponseBoardDto(board);
        return new ResponseDto<>(HttpStatus.OK, dto);
    }

    // 게시판 좋아요
    @Transactional
    public int likeBoardByBoardId(long boardId, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_USERNAME));
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_BOARD));
        BoardLike boardLike = BoardLike.builder()
                .board(board)
                .user(user)
                .build();

        BoardLike boardLikePS = boardLikeRepository.findByBoardAndUser(board, user).orElse(null);

        if (boardLikePS == null) { // null = 한번도 해당 글에 좋아요를 누르지 않은 것
            board.setLikes(board.getLikes() + 1);
            boardLikeRepository.save(boardLike);

            boardRepository.save(board);
        }


        return board.getLikes();
    }

    // 게시판 좋아요 취소
    @Transactional
    public int likeCancelBoardByBoardId(long boardId, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_USERNAME));
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_BOARD));

        BoardLike boardLikePS = boardLikeRepository.findByBoardAndUser(board, user).orElse(null);
        if (boardLikePS != null) { // null = 한번도 해당 글에 좋아요를 누르지 않은 것
            board.setLikes(board.getLikes() - 1);
            boardLikeRepository.delete(boardLikePS);

            boardRepository.save(board);
        }


        return board.getLikes();
    }

    @Transactional
    public void postBoard(RequestBoardDto dto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_USERNAME));
        dto.setUser(user);
        boardRepository.save(dto.toBoard());
    }

    @Transactional
    public void postComment(RequestCommentDto dto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_USERNAME));
        dto.setUser(user);
        Board board = boardRepository
                .findById(dto.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("Can not find board"));
        commentRepository.save(dto.toComment(board));

    }

    @Transactional
    public void postRecomment(RequestRecommentDto dto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_USERNAME));
        dto.setUser(user);
        Comment comment = commentRepository
                .findById(dto.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("Can not find comment"));
        recommentRepository.save(dto.toRecomment(comment));
    }


    //------------------------------------------------------------------

    private User getUser(HttpServletRequest request) {
        RequestToken requestToken = new RequestToken(request);
        String username = requestToken.getUsername().orElseThrow(() -> new IllegalArgumentException("Can not find username"));
        return userRepository.findByUsername(username).orElseThrow();
    }

    @Transactional
    public String updateBoard(Long id, RequestBoardDto dto, HttpServletRequest request) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 글이 존재하지 않습니다."));
        User user = getUser(request); // id번의 코멘트의 User id가 일치할때만 동작하게끔
        if (board.getUser() == user) {
            board.update(dto.getTitle(), dto.getContent()); // image 추후 도입
            return "게시글 수정 성공";
        }
        return "본인의 게시글이 아닙니다.";


    }


    @Transactional
    public String deleteBoard(Long id, HttpServletRequest request) {

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 글이 존재하지 않습니다."));
        User user = getUser(request);
        if (board.getUser() == user) {
            boardRepository.delete(board);
            return "게시글 삭제 성공";
        }
        return "본인의 게시글이 아닙니다.";


    }


    @Transactional
    public String updateComment(Long id, RequestBoardDto dto, HttpServletRequest request) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                // Comment repo에 조건문 추가해서 board id와 userid 둘다 맞는 것만 가져오려다가
                // 생각해보니까 댓글이 여러개 달았을 때 곤란할꺼같아서 Comment id로 가져오는 걸로 해둠.

                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        User user = getUser(request); // id번의 코멘트의 User id가 일치할때만 동작하게끔
        if (comment.getUser() == user) {
            comment.update(dto.getContent()); // image 추후 도입
            return "댓글 수정 성공";
        }
        return "본인의 댓글이 아닙니다.";


    }

    @Transactional
    public String deleteComment(Long id, HttpServletRequest request) {

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        User user = getUser(request);
        if (comment.getUser() == user) {
            commentRepository.delete(comment);
            return "댓글 삭제 성공";
        }
        return "본인의 댓글이 아닙니다.";


    }

    @Transactional
    public String updateRecomment(Long id, RequestBoardDto dto, HttpServletRequest request) {
        Recomment recomment = recommentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 대댓글이 존재하지 않습니다."));
        User user = getUser(request); // id번의 코멘트의 User id가 일치할때만 동작하게끔
        if (recomment.getUser() == user) {
            recomment.update(dto.getContent()); // image 추후 도입
            return "대댓글 수정 성공";
        }
        return "본인의 대댓글이 아닙니다.";


    }

    @Transactional
    public String deleteRecomment(Long id, HttpServletRequest request) {

        Recomment recomment = recommentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 대댓글이 존재하지 않습니다."));
        User user = getUser(request);
        if (recomment.getUser() == user) {
            recommentRepository.delete(recomment);
            return "대댓글 삭제 성공";
        }
        return "본인의 대댓글이 아닙니다.";


    }


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
