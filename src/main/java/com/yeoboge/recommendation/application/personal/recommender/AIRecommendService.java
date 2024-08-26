package com.yeoboge.recommendation.application.personal.recommender;

import com.yeoboge.recommendation.application.personal.annotation.Recommender;
import com.yeoboge.recommendation.application.personal.dto.RecommendationContextDto;
import com.yeoboge.recommendation.core.boardgame.BoardGame;
import com.yeoboge.recommendation.core.boardgame.BoardGameRepository;
import com.yeoboge.recommendation.core.boardgame.Genre;
import com.yeoboge.recommendation.core.boardgame.dto.BoardGameThumbnailDto;
import com.yeoboge.recommendation.global.util.RestClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Recommender
@RequiredArgsConstructor
public class AIRecommendService implements RecommendService {
    public static final String CATEGORY_PREFIX = "내가 좋아하는 ";
    public static final String CATEGORY_SUFFIX = " 보드게임 🎲";

    private final BoardGameRepository repository;
    @Value("${api.base.url}")
    private String baseUrl;

    @Override
    public String getCategory(RecommendationContextDto context) {
        String genre = Genre.ofCode(context.genreId()).getName();
        return CATEGORY_PREFIX + genre + CATEGORY_SUFFIX;
    }

    @Override
    public List<BoardGameThumbnailDto> getRecommendations(RecommendationContextDto context) {
        List<Long> recommendedIds = getRecommendedBoardGameIds(context.userId(), context.genreId());
        return mapThumbnailFromBoardGame(recommendedIds);
    }

    private List<Long> getRecommendedBoardGameIds(long userId, int genreId) {
        RestClient client = RestClientUtil.createClient(baseUrl);
        Optional<Response> result = Optional.ofNullable(
                client.post()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new Request(userId, genreId))
                        .retrieve()
                        .body(Response.class)
        );

        return result.isPresent() ? result.get().result : Collections.emptyList();
    }

    private List<BoardGameThumbnailDto> mapThumbnailFromBoardGame(List<Long> ids) {
        List<BoardGame> boardGames = repository.findBoardGamesByIdIsIn(ids);
        return BoardGameThumbnailDto.fromList(boardGames);
    }

    private record Request(long user_id, int genre_id) {}
    private record Response(List<Long> result) { }
}
