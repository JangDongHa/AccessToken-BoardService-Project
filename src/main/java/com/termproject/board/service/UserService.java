package com.termproject.board.service;


import com.termproject.board.domain.user.User;
import com.termproject.board.dto.*;

import java.util.List;

public interface UserService {
    ResponseDto<String> joinUser(RequestUserDto requestUserDto);
    public List<ResponseBoardDto> getAllBoardsByUser(String username);
    List<ResponseBoardDto> getAllLikeBoardsByUser(String username);
    List<ResponseCommentDto> getAllCommentsByUser(String username);
    List<ResponseCommentDto> getAllLikeCommentsByUser(String username);
    List<ResponseRecommentDto> getAllRecommentByUser(String username);
    List<ResponseRecommentDto> getAllLikeRecommentByUser(String username);
}
