package com.yeoboge.recommendation.application.personal;

import com.yeoboge.recommendation.application.personal.dto.PersonalRecommendationDto;
import com.yeoboge.recommendation.core.boardgame.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalRecommendService {
    private final UserFavoriteGenreService genreService;
    private final AsyncRecommendService recommendService;

    public PersonalRecommendationDto getPersonalRecommendation(long userId) {
        List<Genre> favoriteGenres = genreService.getUserFavoriteGenreWithIds(userId);
        return recommendService.getAsyncPersonalRecommendation(userId, favoriteGenres);
    }
}
