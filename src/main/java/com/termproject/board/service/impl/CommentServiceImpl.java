package com.termproject.board.service.impl;

import com.termproject.board.domain.board.Board;
import com.termproject.board.domain.comment.CommentRepository;
import com.termproject.board.dto.RequestCommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl {
    @Autowired
    private CommentRepository commentRepository;

}
