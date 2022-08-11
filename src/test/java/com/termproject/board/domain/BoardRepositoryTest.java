package com.termproject.board.domain;


import com.termproject.board.domain.board.Board;
import com.termproject.board.domain.board.BoardRepository;
import com.termproject.board.domain.user.User;
import com.termproject.board.domain.user.UserRepository;
import com.termproject.board.dto.RequestBoardDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    private final String title = "board title";
    private final String content = "board content";
    private final User user = User.builder().username("test").build();

    @BeforeEach
    public void settingData(){
        RequestBoardDto boardDto = new RequestBoardDto(title, content, user);

        boardRepository.save(boardDto.toBoard());
    }

    @Test
    public void createTest(){
        // Given
        String title = "board save title";
        String content = "board content";
        User user = User.builder().username("test").build();
        Board board = new RequestBoardDto(title, content, user).toBoard();

        // When
        Board boardPS = boardRepository.save(board);

        // Then
        assertEquals(board, boardPS);
    }

    @Test
    public void readTest(){
        // Given
        RequestBoardDto boardDto = new RequestBoardDto(title, content, user);
        Board board = boardDto.toBoard();

        // When
        Board boardPS = boardRepository.findById(1L).orElseThrow();

        // Then
        assertEquals(board.getTitle(), boardPS.getTitle());
        assertEquals(board.getContent(), boardPS.getContent());
        assertEquals(board.getUser(), boardPS.getUser());
    }

    @Test
    public void updateTest(){
        // Given
        Board board = Board.builder()
                .id(1L)
                .title("update title")
                .content("update content")
                .user(user)
                .build();

        // When
        Board boardPS = boardRepository.save(board);

        // Then
        assertEquals(board.getTitle(), boardPS.getTitle());
    }
}
