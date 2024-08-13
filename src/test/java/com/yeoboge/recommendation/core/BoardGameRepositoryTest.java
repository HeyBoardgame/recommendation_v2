package com.yeoboge.recommendation.core;

import com.yeoboge.recommendation.core.boardgame.BoardGame;
import com.yeoboge.recommendation.core.boardgame.BoardGameRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class BoardGameRepositoryTest {
    @Autowired
    private BoardGameRepository repository;

    @Test
    @DisplayName("보드게임 단일 조회 테스트")
    public void select_board_game_by_id() {
        // given
        long boardGameId = 1;

        // when
        Optional<BoardGame> actual = repository.findById(boardGameId);

        // then
        assert actual.isPresent();
    }
}
