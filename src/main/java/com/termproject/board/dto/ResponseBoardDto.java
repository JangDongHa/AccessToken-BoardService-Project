package com.termproject.board.dto;

import com.termproject.board.domain.board.Board;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseBoardDto {
    private String title;
    private String content;
    private int likes;

    public void setData(Board board){
        this.title = board.getTitle();
        this.content = board.getContent();
        this.likes = board.getLikes();
    }
}
