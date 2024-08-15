package com.yeoboge.recommendation.core;

import com.yeoboge.recommendation.core.boardgame.BoardGame;
import com.yeoboge.recommendation.core.boardgame.BoardGameRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class BoardGameRepositoryTest {
    @Autowired
    private BoardGameRepository repository;

    @Test
    @DisplayName("Id 조건 없이 조회 시 조회되는 BoardGame 엔티티는 아무것도 없어야 함")
    public void select_board_games_with_empty_ids() {
        // given
        List<Long> ids = Collections.emptyList();

        // when
        List<BoardGame> actual = repository.findBoardGamesByIdIsIn(ids);

        // then
        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Id로 여러 개의 보드게임 조회")
    public void select_board_games_with_ids() {
        // given
        List<Long> ids = List.of(1L, 2L, 3L);
        List<BoardGame> expected = new ArrayList<>();
        for (long i = 1; i <= 3; i++) {
            BoardGame boardGame = BoardGame.builder().id(i).build();
            expected.add(boardGame);
        }

        // when
        List<BoardGame> actual = repository.findBoardGamesByIdIsIn(ids);

        // then
        int count = 0;
        for (int i = 0; i < expected.size(); i++) {
            BoardGame e = expected.get(i);
            BoardGame a = actual.get(i);
            if (e.getId().equals(a.getId())) count++;
        }
        assertEquals(count, expected.size());
    }
}
