package com.termproject.board.dto;

import com.termproject.board.domain.board.Board;
import com.termproject.board.domain.comment.Comment;
import com.termproject.board.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestCommentDto {
    private String content;
    private User user;
    private long boardId;

    public Comment toComment(Board board){
        return Comment.builder()
                .content(this.content)
                .user(this.user)
                .board(board)
                .build();
    }
}
