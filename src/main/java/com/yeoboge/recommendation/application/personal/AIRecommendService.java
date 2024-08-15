package com.yeoboge.recommendation.application.personal;

import com.yeoboge.recommendation.application.personal.annotation.RecommendService;
import com.yeoboge.recommendation.core.boardgame.BoardGame;
import com.yeoboge.recommendation.core.boardgame.BoardGameRepository;
import com.yeoboge.recommendation.core.boardgame.dto.BoardGameThumbnailDto;
import com.yeoboge.recommendation.infra.util.RestClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.List;

@RecommendService
@RequiredArgsConstructor
public class AIRecommendService {
    public static final String BASE_URL = "http://localhost:9090/recommends";

    private final BoardGameRepository repository;

    public List<BoardGameThumbnailDto> getRecommendedBoardGames(long userId, int genreId) {
        List<Long> recommendedIds = getRecommendedBoardGameIds(userId, genreId);
        return mapThumbnailFromBoardGame(recommendedIds);
    }

    private List<Long> getRecommendedBoardGameIds(long userId, int genreId) {
        RestClient client = RestClientUtil.createClient(BASE_URL);
        Response result = client.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new Request(userId, genreId))
                .retrieve()
                .body(Response.class);
        return result.result;
    }

    private List<BoardGameThumbnailDto> mapThumbnailFromBoardGame(List<Long> ids) {
        List<BoardGame> boardGames = repository.findBoardGamesByIdIsIn(ids);
        return BoardGameThumbnailDto.fromList(boardGames);
    }

    private record Request(long user_id, int genre_id) {}
    private record Response(List<Long> result) {}
}
