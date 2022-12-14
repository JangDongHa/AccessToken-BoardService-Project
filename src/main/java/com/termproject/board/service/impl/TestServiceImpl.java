package com.termproject.board.service.impl;

import com.termproject.board.domain.board.Board;
import com.termproject.board.domain.board.BoardLike;
import com.termproject.board.domain.board.BoardLikeRepository;
import com.termproject.board.domain.board.BoardRepository;
import com.termproject.board.domain.comment.Comment;
import com.termproject.board.domain.comment.CommentLike;
import com.termproject.board.domain.comment.CommentLikeRepository;
import com.termproject.board.domain.comment.CommentRepository;
import com.termproject.board.domain.recomment.Recomment;
import com.termproject.board.domain.recomment.RecommentLike;
import com.termproject.board.domain.recomment.RecommentLikeRepository;
import com.termproject.board.domain.recomment.RecommentRepository;
import com.termproject.board.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestServiceImpl {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RecommentRepository recommentRepository;

    @Autowired
    private BoardLikeRepository boardLikeRepository;

    @Autowired
    private CommentLikeRepository commentLikeRepository;

    @Autowired
    private RecommentLikeRepository recommentLikeRepository;



    @Transactional
    public void likeBoard(long boardId, User user){
        Board board = boardRepository
                .findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("Can not find board"));
        BoardLike boardLike = BoardLike.builder()
                .board(board)
                .user(user).build();
        boardLikeRepository.save(boardLike);
        board.setLikes(board.getLikes() + 1);

    }



    @Transactional
    public void likeComment(long commentId, User user){
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(()->new IllegalArgumentException("Can not find board"));
        CommentLike commentLike = CommentLike.builder()
                .comment(comment)
                .user(user).build();
        commentLikeRepository.save(commentLike);
        comment.setLikes(comment.getLikes() + 1);
    }



    @Transactional
    public void likeRecomment(long recommentId, User user){
        Recomment recomment = recommentRepository
                .findById(recommentId)
                .orElseThrow(()->new IllegalArgumentException("Can not find board"));
        RecommentLike recommentLike = RecommentLike.builder()
                .recomment(recomment)
                .user(user).build();
        recommentLikeRepository.save(recommentLike);
        recomment.setLikes(recomment.getLikes() + 1);
    }




}
