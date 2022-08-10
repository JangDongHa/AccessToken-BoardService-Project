package com.termproject.board.dto;

import com.termproject.board.domain.board.Board;
import com.termproject.board.domain.comment.Comment;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseBoardDto {

    private Timestamp createTime;
    private Timestamp modifiedDate;
    private String title;
    private String content;
    private int likes;

    private List<Comment> comments;

    public ResponseBoardDto(Board board){
        setData(board);
    }

    public void setData(Board board){
        this.title = board.getTitle();
        this.content = board.getContent();
        this.likes = board.getLikes();
        this.comments = board.getComments();
        this.createTime = board.getCreateTime();
        this.modifiedDate = board.getModifiedDate();
    }
}
