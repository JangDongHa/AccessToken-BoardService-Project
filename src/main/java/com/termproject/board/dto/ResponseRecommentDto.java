package com.termproject.board.dto;

import com.termproject.board.domain.comment.Comment;
import com.termproject.board.domain.recomment.Recomment;
import com.termproject.board.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRecommentDto {
    private String content;
    private int likes;
    private long commentId;

    public ResponseRecommentDto(Recomment recomment){
        setData(recomment);
    }

    public void setData(Recomment recomment){
        this.content = recomment.getContent();
        this.likes = recomment.getLikes();
        this.commentId = recomment.getComment().getId();
    }

}
