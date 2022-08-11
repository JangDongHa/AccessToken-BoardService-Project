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
public class RequestRecommentDto {
    private String content;
    private User user;
    private long commentId;

    public Recomment toRecomment(Comment comment){
        return Recomment.builder()
                .content(content)
                .user(user)
                .comment(comment)
                .build();
    }
}
