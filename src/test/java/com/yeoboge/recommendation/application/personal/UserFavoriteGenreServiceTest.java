package com.yeoboge.recommendation.application.personal;

import com.yeoboge.recommendation.core.boardgame.Genre;
import com.yeoboge.recommendation.core.user.FavoriteGenre;
import com.yeoboge.recommendation.core.user.User;
import com.yeoboge.recommendation.core.user.UserRepository;
import com.yeoboge.recommendation.core.user.dto.FavoriteGenreIdDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@Profile("test")
@ExtendWith(MockitoExtension.class)
public class UserFavoriteGenreServiceTest {
    @InjectMocks
    private UserFavoriteGenreService service;

    @Mock
    private UserRepository repository;

    @Test
    @DisplayName("사용자 선호 장르 DTO 목록 조회")
    public void get_user_favorite_genres() {
        // given
        long userId = 1;
        FavoriteGenre favoriteGenre = FavoriteGenre.builder().genreCode(Genre.ABSTRACT).build();
        List<FavoriteGenre> favoriteGenres = Collections.nCopies(5, favoriteGenre);
        List<FavoriteGenreIdDto> expected = favoriteGenres.stream()
                .map(o -> new FavoriteGenreIdDto(userId, o.getGenreCode().getCode()))
                .toList();
        User user = User.builder().id(userId).favoriteGenres(favoriteGenres).build();

        // when
        when(repository.findByIdWithFavoriteGenre(userId)).thenReturn(Optional.of(user));

        // then
        List<FavoriteGenreIdDto> actual = service.getUserFavoriteGenreWithIds(userId);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("선호 장르 없는 사용자의 DTO 목록 조회")
    public void get_empty_user_favorite_genre() {
        // given
        long userId = 1;
        User user = User.builder().id(userId).favoriteGenres(Collections.emptyList()).build();

        // when
        when(repository.findByIdWithFavoriteGenre(userId)).thenReturn(Optional.of(user));

        // then
        List<FavoriteGenreIdDto> actual = service.getUserFavoriteGenreWithIds(userId);
        assertTrue(actual.isEmpty());
    }
}
