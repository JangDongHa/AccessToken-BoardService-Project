package com.termproject.board.service.impl;

import com.termproject.board.domain.recomment.RecommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommentServiceImpl {
    @Autowired
    private RecommentRepository recommentRepository;
}
