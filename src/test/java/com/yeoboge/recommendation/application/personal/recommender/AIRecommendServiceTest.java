package com.yeoboge.recommendation.application.personal.recommender;

import com.yeoboge.recommendation.application.personal.dto.RecommendationContextDto;
import com.yeoboge.recommendation.core.boardgame.BoardGame;
import com.yeoboge.recommendation.core.boardgame.BoardGameRepository;
import com.yeoboge.recommendation.core.boardgame.Genre;
import com.yeoboge.recommendation.core.boardgame.dto.BoardGameThumbnailDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class AIRecommendServiceTest {
    @InjectMocks
    private AIRecommendService service;

    @Mock
    private BoardGameRepository repository;

    @Test
    @DisplayName("추천 API 결과 기반으로 DTO 목록 생성")
    void get_thumbnail_list_from_api_response() {
        // given
        List<BoardGame> fixtures = boardGameFixtures();
        List<BoardGameThumbnailDto> expected = BoardGameThumbnailDto.fromList(fixtures);
        long userId = 1;
        int genreId = 2;

        // when
        when(repository.findBoardGamesByIdIsIn(anyList())).thenReturn(fixtures);

        // then
        RecommendationContextDto context = new RecommendationContextDto(userId, genreId);
        List<BoardGameThumbnailDto> actual = service.getRecommendations(context);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("추천 API 결과 없을 때 EmptyList 반환")
    void get_empty_list_from_empty_response() {
        // given
        List<BoardGame> fixtures = Collections.emptyList();
        List<BoardGameThumbnailDto> expected = Collections.emptyList();
        long userId = 1;
        int genreId = 2;

        // when
        when(repository.findBoardGamesByIdIsIn(anyList())).thenReturn(fixtures);

        // then
        RecommendationContextDto context = new RecommendationContextDto(userId, genreId);
        List<BoardGameThumbnailDto> actual = service.getRecommendations(context);
        assertEquals(expected, actual);
    }

    private List<BoardGame> boardGameFixtures() {
        BoardGame boardGame = BoardGame.builder()
                .id(1L)
                .name("Azule")
                .genre(Genre.ABSTRACT)
                .build();
        return new ArrayList<>(Collections.nCopies(10, boardGame));
    }
}
