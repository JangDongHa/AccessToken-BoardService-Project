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
import com.termproject.board.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


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


    public String likeBoard(long boardId, User user) {
        Board board = boardRepository
                .findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Can not find board"));

        List<BoardLike> list = boardLikeRepository.findByUserIdAndBoardId( user.getId(), boardId);

        if (list.size() == 0) { //
            BoardLike boardLike = BoardLike.builder()
                    .user(user)
                    .board(board)
                    .build();

            boardLikeRepository.save(boardLike);
            board.setLikes(board.getLikes() + 1);
            return "좋아요를 등록완료했습니다";


        }

        boardLikeRepository.deleteAllByBoardIdAndUserId( boardId, user.getId());
        board.setLikes(board.getLikes() - 1);
        return "좋아요를 취소하였습니다";


    }


    public String likeComment(Long id, User user) {
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Can not find this comment"));


        List<CommentLike> list = commentLikeRepository.findByUserIdAndCommentId(user.getId(), id);

        if (list.size() == 0) { //
            CommentLike commentLike = CommentLike.builder()
                    .user(user)
                    .comment(comment)
                    .build();

            commentLikeRepository.save(commentLike);
            comment.setLikes(comment.getLikes() + 1);
            return "좋아요를 댓글에 등록완료했습니다";


        }

        commentLikeRepository.deleteAllByUserIdAndCommentId(user.getId(), id);
        comment.setLikes(comment.getLikes() - 1);
        return "좋아요를 댓글에서 취소하였습니다";


    }


    public String likeRecomment(Long id, User user) {
        Recomment recomment = recommentRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Can not find this comment"));


        List<RecommentLike> list = recommentLikeRepository.findByUserIdAndRecommentId(user.getId(), recomment.getId());

        if (list.size() == 0) { //
            RecommentLike recommentLike = RecommentLike.builder()
                    .recomment(recomment)
                    .user(user).build();

            recommentLikeRepository.save(recommentLike);
            recomment.setLikes(recomment.getLikes() + 1);
            return "좋아요를 대댓글에 등록완료했습니다";


        }

        recommentLikeRepository.deleteAllByUserIdAndRecommentId(user.getId(), recomment.getId());
        recomment.setLikes(recomment.getLikes() - 1);


        return "좋아요를 대댓글에서 취소하였습니다";


    }
}
