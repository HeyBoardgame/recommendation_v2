package com.yeoboge.recommendation.application.personal;

import com.yeoboge.recommendation.core.boardgame.dto.BoardGameThumbnailDto;
import com.yeoboge.recommendation.infra.util.RestClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AIRecommendService implements RecommendService {
    public static final String BASE_URL = "http://localhost:9090/recommends";

    @Override
    public List<BoardGameThumbnailDto> getRecommendedBoardGames() {
        List<Long> recommendedIds = getRecommendedBoardGameIds();
        return null;
    }

    private List<Long> getRecommendedBoardGameIds() {
        RestClient client = RestClientUtil.createClient(BASE_URL);
        Response result = client.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new Request(1, 1))
                .retrieve()
                .body(Response.class);
        return result.result;
    }

    private record Request(int user_id, int genre_id) {}
    private record Response(List<Long> result) {}
}
