package com.termproject.board.like.service;


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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {


    private final BoardLikeRepository boardLikeRepository;

    private final BoardRepository boardRepository;

    private final CommentRepository commentRepository;

    private final CommentLikeRepository commentLikeRepository;

    private final RecommentLikeRepository recommentLikeRepository;

    private final RecommentRepository recommentRepository;

//    public Optional<PostTest> add(Long id){
//        PostTest test = new PostTest(1L,0L);     // test용 코드 추후 repository 변경
//        test.setLikes(test.getLikes()+1L);
//        postTestRepository.save(test);
//
//        return Optional.of(test);
//
//    }
//
//    public Optional<PostTest> sub(Long id){
//        PostTest test = new PostTest(1L,0L);     // test용 코드 추후 repository 변경
//        test.setLikes(test.getLikes()-1L);
//        postTestRepository.save(test);
//
//        return Optional.of(test);
//
//    }




    public void likeBoard(Long id){
        Board board = boardRepository
                .findById(id)
                .orElseThrow(()->new IllegalArgumentException("Can not find this board"));
        BoardLike boardLike = BoardLike.builder()
                .board(board)
                .user(board.getUser()).build();
        boardLikeRepository.save(boardLike);
        board.setLikes(board.getLikes() + 1);

    }


    public void unlikeBoard(Long id){
        Board board = boardRepository
                .findById(id)
                .orElseThrow(()->new IllegalArgumentException("Can not find this board"));
        BoardLike boardLike = BoardLike.builder()
                .board(board)
                .user(board.getUser()).build();
        boardLikeRepository.save(boardLike);
        board.setLikes(board.getLikes() - 1);

    }






    public void likeComment(Long id){
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(()->new IllegalArgumentException("Can not find this comment"));
        CommentLike commentLike = CommentLike.builder()
                .comment(comment)
                .user(comment.getUser()).build();
        commentLikeRepository.save(commentLike);
        comment.setLikes(comment.getLikes() + 1);
    }




    public void unikeComment(Long id){
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(()->new IllegalArgumentException("Can not find this comment"));
        CommentLike commentLike = CommentLike.builder()
                .comment(comment)
                .user(comment.getUser()).build();
        commentLikeRepository.save(commentLike);
        comment.setLikes(comment.getLikes() - 1);
    }


    public void likeRecomment(Long id){
        Recomment recomment = recommentRepository
                .findById(id)
                .orElseThrow(()->new IllegalArgumentException("Can not find board"));
        RecommentLike recommentLike = RecommentLike.builder()
                .recomment(recomment)
                .user(recomment.getUser()).build();
        recommentLikeRepository.save(recommentLike);
        recomment.setLikes(recomment.getLikes() + 1);
    }



    public void unlikeRecomment(Long id){
        Recomment recomment = recommentRepository
                .findById(id)
                .orElseThrow(()->new IllegalArgumentException("Can not find board"));
        RecommentLike recommentLike = RecommentLike.builder()
                .recomment(recomment)
                .user(recomment.getUser()).build();
        recommentLikeRepository.save(recommentLike);
        recomment.setLikes(recomment.getLikes() - 1);
    }







}
