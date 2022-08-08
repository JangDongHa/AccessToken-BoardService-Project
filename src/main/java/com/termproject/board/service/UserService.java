package com.termproject.board.service;


import com.termproject.board.domain.user.User;
import com.termproject.board.dto.*;

import java.util.List;

public interface UserService {
    ResponseDto<String> joinUser(RequestUserDto requestUserDto);
    public List<ResponseBoardDto> getAllBoardsByUser(User user);
    List<ResponseBoardDto> getAllLikeBoardsByUser(User user);
    List<ResponseCommentDto> getAllCommentsByUser(User user);
    List<ResponseCommentDto> getAllLikeCommentsByUser(User user);
    List<ResponseRecommentDto> getAllRecommentByUser(User user);
    List<ResponseRecommentDto> getAllLikeRecommentByUser(User user);
}
