package com.termproject.board.scheduler;

import com.termproject.board.domain.board.Board;
import com.termproject.board.domain.board.BoardRepository;
import com.termproject.board.service.impl.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoardScheduler {
    @Autowired
    private BoardServiceImpl boardService;

    // 초 분 시 일 월 주
    @Scheduled(cron = "0 0 1 * * *")
    public void deleteNoCommentBoards(){
        boardService.deleteNoCommentBoards();
    }

}
