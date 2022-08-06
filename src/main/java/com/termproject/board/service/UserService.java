package com.termproject.board.service;


import com.termproject.board.domain.user.User;
import com.termproject.board.dto.RequestUserDto;
import com.termproject.board.dto.ResponseDto;

public interface UserService {
    ResponseDto<String> joinUser(RequestUserDto requestUserDto);
}
