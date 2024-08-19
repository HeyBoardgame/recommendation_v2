package com.yeoboge.recommendation.application.personal;

import com.yeoboge.recommendation.core.boardgame.BoardGameRepository;
import com.yeoboge.recommendation.core.boardgame.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserFavoriteGenreServiceTest {
    @InjectMocks
    private UserFavoriteGenreService service;

    @Mock
    private BoardGameRepository repository;

    @Test
    @DisplayName("사용자 선호 장르 DTO 목록 조회")
    void get_user_favorite_genres() {
        // given
        long userId = 1;
        Genre favoriteGenre = Genre.ABSTRACT;
        List<Genre> expected = Collections.nCopies(3, favoriteGenre);

        // when
        when(repository.findUserFavoriteGenre(userId)).thenReturn(expected);

        // then
        List<Genre> actual = service.getUserFavoriteGenreWithIds(userId);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("선호 장르 없는 사용자의 DTO 목록 조회")
    void get_empty_user_favorite_genre() {
        // given
        long userId = 1;

        // when
        when(repository.findUserFavoriteGenre(userId)).thenReturn(Collections.emptyList());

        // then
        List<Genre> actual = service.getUserFavoriteGenreWithIds(userId);
        assertTrue(actual.isEmpty());
    }
}
