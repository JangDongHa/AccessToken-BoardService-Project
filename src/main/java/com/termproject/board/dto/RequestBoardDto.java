package com.termproject.board.dto;

import com.termproject.board.domain.board.Board;
import com.termproject.board.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestBoardDto {
    private String title;
    private String content;
    private User user;

    public Board toBoard(){
        return Board.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }
}
