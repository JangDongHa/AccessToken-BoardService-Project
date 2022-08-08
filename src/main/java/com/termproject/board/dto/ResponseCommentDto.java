package com.termproject.board.dto;

import com.termproject.board.domain.comment.Comment;
import com.termproject.board.domain.recomment.Recomment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCommentDto {
    private String content;
    private long boardId;
    private int likes;
    private List<Recomment> recomments;

    public ResponseCommentDto(Comment comment){
        setData(comment);
    }

    public void setData(Comment comment){
        this.content = comment.getContent();
        this.boardId = comment.getBoard().getId();
        this.likes = comment.getLikes();
        this.recomments = comment.getRecomments();
    }
}
