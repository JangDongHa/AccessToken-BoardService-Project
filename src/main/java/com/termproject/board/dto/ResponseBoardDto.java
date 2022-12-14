package com.termproject.board.dto;

import com.termproject.board.domain.board.Board;
import com.termproject.board.domain.comment.Comment;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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

    private String filePath;

    private List<Comment> comments;

    private int commentsCount;

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
        this.commentsCount = board.getComments().size();
        this.filePath = board.getImage();
    }
}
