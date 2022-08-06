package com.termproject.board.dto;

import com.termproject.board.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestUserDto {
    private String username;
    private String password;

    public User toUser(){
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
