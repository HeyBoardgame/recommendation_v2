package com.yeoboge.recommendation.application.personal.recommender;

import com.yeoboge.recommendation.application.personal.dto.RecommendationContextDto;
import com.yeoboge.recommendation.core.boardgame.BoardGame;
import com.yeoboge.recommendation.core.boardgame.BoardGameRepository;
import com.yeoboge.recommendation.core.boardgame.Genre;
import com.yeoboge.recommendation.core.boardgame.dto.BoardGameThumbnailDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserBookmarkRecommendServiceTest {
    @InjectMocks
    private UserBookmarkRecommendService service;

    @Mock
    private BoardGameRepository repository;

    @Test
    void get_user_bookmarked_board_games() {
        // given
        long userId = 1;
        BoardGame boardGame = BoardGame.builder()
                .name("board game")
                .genre(Genre.ABSTRACT)
                .build();
        List<BoardGame> fixtures = Collections.nCopies(BoardGameRepository.NUM_RECOMMENDED_BOARD_GAMES, boardGame);
        List<BoardGameThumbnailDto> expected = BoardGameThumbnailDto.fromList(fixtures);

        // when
        when(repository.findUserBookmarkedBoardGames(userId)).thenReturn(fixtures);

        // then
        RecommendationContextDto context = new RecommendationContextDto(userId, null);
        List<BoardGameThumbnailDto> actual = service.getRecommendations(context);
        assertEquals(expected, actual);
    }
}
